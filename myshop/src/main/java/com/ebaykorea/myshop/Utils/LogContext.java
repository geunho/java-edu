package com.ebaykorea.myshop.Utils;

public class LogContext {
    // main method가 실행되면 main thread가 실행된다.
    // 자바 프로그램은 모든 thread가 종료돼야 종료된다. main thread가 종료돼도 다른 thread가 종료돼야 종료됨.
    // 내부적으로 map을 가짐.
    // 현재 Thread 정보를 구해서 그 thread 정보로 채우거나 새로 생성함.
    // 현재 thread 정보가 key 값.
    public static ThreadLocal<Long> time = new ThreadLocal<Long>();
}
