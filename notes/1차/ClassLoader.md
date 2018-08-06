# ClassLoader
JVM에 Java class를 runtime에 동적으로 불러오는 역할을 한다. Class loader는 JRE의 일부로서 이 덕분에 JVM이 Java 프로그램이 동작할때 파일과 파일 시스템에 대해서 알 필요가 없도록 도와준다.
Java class는 메모리에 한번에 올라오지 않는데, 애플리케이션에서 필요로 할때 로드되는데 이 역할을 class loader가 하는 것이다.

### ClassLoader의 종류
JVM이 시작되면 세 개의 class loader가 사용된다:
1. Bootstrap class loader : Core java 라이브러리가 위치한 `<JAVA_HOME>/jre/lib` 디렉토리의 클래스들을 불러온다. native 코드로 작성된 class loader이다. 다른 class loader 인스턴스들을 불러온다.
2. Extensions class loader : extensions 디렉토리`<JAVA_HOME>/jre/lib/ext` 위치한 라이브러리를 불러온다. `sun.misc.Launcher$ExtClassLoader`에 의해 구현된 class loader이다.
3. System(Application) class loader : `<CLASSPATH>` 환경 변수에 위치한 클래스를 로드한다. `sun.misc.Launcher$AppClassLoader` 클래스로 구현된 class loader이다.

```java
public class Hello {
...
  public void printClassLoader() {
    // ArrayList 클래스의 클래스로더. Bootstrap class loader
    System.out.println("ClassLoader: ArrayList[" + ArrayList.class.getClassLoader() + 
"]");
    // Logging 클래스의 클래스로더. Extensions class loader
    System.out.println("ClassLoader: Logging[" + Logging.class.getClassLoader() + 
"]");
    // 현재 메서드가 실행되는 클래스의 클래스로더. System(Application) class loader
     System.out.println("ClassLoader: Hello[" + Hello.class.getClassLoader() + "]");
  }
...
}
```

메서드 출력 결과는 다음과 같다 : 
```bash
ClassLoader: ArrayList[null]
ClassLoader: Logging[sun.misc.Launcher$ExtClassLoader@3wded153]
ClassLoader: Hello[sun.misc.Launcher$AppClassLoader@73d16e93]
```

java.*으로 시작하는 패키지는 Java Core 라이브러리로 Bootstrap class loader에 의해 로드되므로 getClassLoader() 결과는 null이 된다. Bootstrap class loader는 java 클래스가 아니라 native 로 작성되었기 때문이다. 따라서 JVM마다 bootstrap class loader의 동작 특성이 서로 다르다.
javax.*로 시작하는 패키지는 Java Core 라이브러리의 extension 클래스를 로드하며, 마지막 출력의 AppClassLoader는 System class loader로 CLASSPATH의 사용자의 클래스를 로드한다.

![alt Core and Extension class](https://docs.oracle.com/javase/tutorial/figures/ext/exta.gif)

### ClassLoader의 동작 방식
클래스로더가 클래스를 찾을 때 부모 클래스로더에게 위임해서 클래스가 있는지 찾는다. 이 과정은 recursive하게 일어나는데 마지막 자식 클래스로더까지 클래스를 찾지 못한다면 ClassNotFoundException 예외를 던진다.
즉, 클래스로더는 부모-자식 클래스로 계층을 이루고 있다.
가장 상위 클래스는 Bootstrap class loader이며, Extension class loader는 이 클래스로더의 자식 클래스로더이다. 그 다음 System class loader가 이 extension class loader의 자식이 되고, 최종적으로 사용자가 정의한 user-defined class loader가 그 아래 위치하게 된다. 물론 다른 사용자 클래스로더는 또 다른 사용자 클래스로더의 자식이 될 수 있다.

![alt Class loader layer](https://t1.daumcdn.net/cfile/tistory/241AC545555344A531)


### References
1. https://en.wikipedia.org/wiki/Java_Classloader
2. http://www.baeldung.com/java-classloaders
3. https://docs.oracle.com/javase/tutorial/ext/
4. http://blueyikim.tistory.com/37


