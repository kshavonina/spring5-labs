package demo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Log4j2
public class LoggingAspect {

    @Around("execution(* *service.*(..))")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        log.info("Call method {} with args {}", methodName, methodArgs);

        Object result = thisJoinPoint.proceed();

        log.info("Method {} returns {}", methodName, result);
        return result;
    }
}

