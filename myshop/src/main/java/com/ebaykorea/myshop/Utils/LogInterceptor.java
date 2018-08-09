package com.ebaykorea.myshop.Utils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

public class LogInterceptor extends HandlerInterceptorAdapter {
    // handler는 사용자가 요청한 Controller의 정보가 담겨있다.
    // false return하면 여기서 종료된다.
    // 이 Interceptor는 설정파일에서 등록한다.
    // 걸린 시간을 구한다면?
    // request에 pre가 시작된 시간을 넣고
    // pro에서 끝난 시간을 채우면 걸린 시간을 구할 수 있따.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 내가 시도한 코드
        Date requestStartingTime = Calendar.getInstance().getTime();
        request.setAttribute("RequestStartingTime", requestStartingTime);
        */

        // 1차 코드
        //request.setAttribute("startTime", System.nanoTime());

        // ThreadLocal 코드
        LogContext.time.set(System.nanoTime());

        System.out.println("preHandle :" + handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 내가 시도한 코드
        /*
        Date requestEndTime = Calendar.getInstance().getTime();
        Date requestStartingTime = (Date)request.getAttribute(("RequestStartingTime"));
        */

        // 1차 코드
        //long endTime = System.nanoTime();
        //long startTime = (long)request.getAttribute(("startTime"));

        // ThreadLocal을 사용한 코드
        long endTime = System.nanoTime();
        long startTime = LogContext.time.get();
        System.out.println("postHandle : " + handler + " : " + (endTime - startTime));
    }
}
