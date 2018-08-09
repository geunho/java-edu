package com.ebaykorea.myshop;

import java.lang.reflect.Method;

public class Exam1 {
    public static void main(String[] args) throws Exception {
        // class 타입은 class 정보를 가짐
        // class 정보는 class path에 있어야 한다.
        Class clazz = Class.forName("com.ebaykorea.myshop.MyData");
        // 이미 class loader에 의해 메모리에 이미 올라가 있따. 그러므로 위에서 한 번만 호출됨.
        // 아직 instance가 생성된건 아니다.
        Class clazz2 = Class.forName("com.ebaykorea.myshop.MyData");

        // Object의 메소드도 출력해줌
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        // 객체 생성 > new MyData()와 동일
        Object obj = clazz.newInstance();

        // 문자열만 가지고 객체를 생성하고 메소드를 실행할 수 있다.
        // java reflection 기술
        Method m = clazz.getMethod("print", null);
        m.invoke(obj, null);
    }
}

class MyData {
    static int i;
    static {
        i = 5;
        System.out.println("static@@");
    }

    public MyData() {
        System.out.println("MyData()");
    }

    public void print() {
        System.out.println("test");
    }
}
