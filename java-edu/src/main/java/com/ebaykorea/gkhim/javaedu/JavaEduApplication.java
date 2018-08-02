package com.ebaykorea.gkhim.javaedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaEduApplication {
//  @Bean
//  /** bean 마다 id를 갖는데 그게 메서드 이름이다.
//   *  따라서 Bean 메서드명은 모두 고유해야 한다.
//   */
//  public HelloController helloController() {
//    return new HelloController(helloService());
//  }
//
//  @Bean
//  public HelloController helloController2() {
//    return new HelloController(helloService());
//  }
//
//  @Bean
//  public HelloService helloService() {
//    /**
//     * 만약 helloService()가 두 번 호출되면 CGLIB에서 Proxy 메서드를 생성하게 된다.
//     */
//    System.out.println(getClass().getName()); // 출력 결과를 보면 JavaEduApplication을 출력하지 않는다.
//    return new HelloService();
//  }

	public static void main(String[] args) {
    /** class parameter -> java config 클래스가 포함되는 것
     * SpringApplication에는 Bean container가 내장되어 있다.
     * Bean container -> WebApplicationContext가 생성
     * 그리고 Bean을 생성
     */
		SpringApplication.run(JavaEduApplication.class, args);
	}
}
