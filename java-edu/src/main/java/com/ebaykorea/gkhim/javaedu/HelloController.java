package com.ebaykorea.gkhim.javaedu;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// bean container가 관리하도록 해야함.
// WebApp 클래스에 @Bean 어노테이션으로 등록
//public class HelloController {
//  private HelloService helloService;
//  public HelloController(HelloService helloService) {
//    this.helloService = helloService;
//    System.out.println("HelooController!!!");
//  }
//}

@RestController
public class HelloController {
  public HelloController() {
    System.out.println("HelloController ! ! ");
  }

  // @RequestMapping(method = RequestMethod.GET)와 같다. Spring >= 4.3 부터 도입
  @GetMapping("/hello") // @GetMapping(path = "/hello")
  public String sayHello() {
    return "hello";
  }

  // @RequestParam은 query string 값을 어떻게든 채우려고 한다.
  // 만약 string이 아닌 파라미터로 받게 되면 그 형식에 맞는 값이 아닌 경우 형변환 오류로 400 에러를 발생시킨다.
  // 예. age 에 숫자가 아닌 값을 입력.
  @GetMapping("/hello2") // /hello2?name=kim&age=20
  public String sayHello2(@RequestParam(name = "name", required = true)String name,
                          @RequestParam(name = "age", required = false, defaultValue = "0")int age) {
    return "hello, " + name + " " + age;
  }

  // servlet request에 직접 접근하고 제어할 수 있음
  // 이 클래스가 파라미터로 설정되면 spring은 최대한 값들을 채우려고 한다.
  // import javax.servlet.http.HttpServletRequest 패키지 위치를 ㄱ보듯이 왠만하면 이러한 형태는 지양한다.
  @GetMapping("/hello3")
  public String sayHello3(HttpServletRequest request) {
    String name = request.getParameter("name");
    String age = request.getParameter("age");

    return "hello, " + name + " " + age;
  }

  @GetMapping("/hello4")
  public String sayHello4(@ModelAttribute HelloDTO hello) {
    return "hello, " + hello.getName() + " " + hello.getAge();
  }

  @PostMapping("/hello5")
  public String sayHello5() {
    return "hello from post method,";
  }

  @PostMapping("/hello6")
  public String sayHello6(@RequestParam(name = "name", required = true)String name,
                          @RequestParam(name = "age", required = false, defaultValue = "0")int age) {
    return "hello, " + name + " " + age;
  }


  @PostMapping("/hello7")
  public String sayHello7(@RequestBody HelloDTO hello) {
    return "hello, " + hello.getName() + " " + hello.getAge();
  }

  @GetMapping("/hello8")
  public HelloDTO sayHello8() {
    HelloDTO hello = new HelloDTO();
    hello.setName("KIM");
    hello.setAge(50);

    return hello;
  }

  @GetMapping("/hello9")
  public HelloDTO sayHello9(@ModelAttribute HelloDTO hello) {
    return hello;
  }
}