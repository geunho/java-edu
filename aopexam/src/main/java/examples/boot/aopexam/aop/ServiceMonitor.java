package examples.boot.aopexam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {
  @Before("execution(* examples..*Service.*(..))")
  public void beforeExecute(JoinPoint joinPoint) {
    System.out.println("[Before execution] name : " + joinPoint.getSignature().getName() );
  }

    @AfterReturning("execution(* examples..*Service.*(..))") // example로 시작하면서 service로 끝나는 클래스의 모든 메서드 시그니쳐에 대해 실행
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("[After returning] name : " + joinPoint.getSignature().getName() );
        System.out.println("Completed: " + joinPoint);
    }

    @AfterThrowing(value = "execution(* examples..*Service.*(..))", throwing = "ex")
    public void afterException(JoinPoint joinPoint, Exception ex) {
      System.out.println("***********************************");
        System.out.println("exception :" + joinPoint);
        System.out.println(ex.getMessage());
      System.out.println("***********************************");
    }


  @Around("execution(* examples..*Service.*(..))")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("(1) Going to call the method.");
    Object output = pjp.proceed();
    System.out.println("(2) Method execution completed.");
    long elapsedTime = System.currentTimeMillis() - start;
    System.out.println("(3)   Method execution time: " + elapsedTime + " milliseconds.");
    return output;
  }
}
