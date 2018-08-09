package com.ebaykorea.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// com.~ 패키지에 속해있다.
// 이 패키지 내에 있는 패키지를 모두 찾는다.
// Application은 가능하면 Base 패키지 바로 아래에 넣는게 좋다.
// 한 번 더 하위에 넣으면 나머지 패키지들을 다 지정해야한다.
@SpringBootApplication
public class MyshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyshopApplication.class, args);
	}
}
