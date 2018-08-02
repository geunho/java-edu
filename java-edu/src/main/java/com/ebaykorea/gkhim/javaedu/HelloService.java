package com.ebaykorea.gkhim.javaedu;

// bean container가 관리하도록 해야함.
// WebApp 클래스에 @Bean 어노테이션으로 등록
public class HelloService {
  public HelloService() {
    System.out.println("HelooService!!!");
  }
}
