package demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {
    @Pointcut("within(lab.service..*)")
    public void inServiceLayer() {
    }

    @Pointcut("within(lab.dao..*)")
    public void inDataAccessLayer() {
    }

    @Pointcut("execution(* lab.dao.*.*(..))")
    public void dataAccessOperation() {
    }
}
