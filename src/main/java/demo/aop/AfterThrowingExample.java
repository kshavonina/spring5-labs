package demo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Log4j2
public class AfterThrowingExample {

    @AfterThrowing(
            pointcut = "demo.aop.SystemArchitecture.dataAccessOperation()",
            throwing = "ex")
    public void doRecoveryActions(Exception ex) {
        log.error(ex.getMessage());
        //...
    }

    @Around("demo.aop.SystemArchitecture.inServiceLayer()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }
}