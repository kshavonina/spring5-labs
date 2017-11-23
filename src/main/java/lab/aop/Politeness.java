package lab.aop;

import lab.model.Customer;
import lab.model.Squishee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Politeness {
    @Before("execution(* sellSquishee(..))")
    public void sayHello(JoinPoint joinPoint) {
        System.out.println("Hello " + ((Customer) joinPoint.getArgs()[0]).getName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        System.out.println(("Is " + ((Squishee) retVal).getName() + " Good Enough? \n"));
    }

    @AfterThrowing("execution(* sellSquishee(..))")
    public void sayYouAreNotAllowed() {
        System.out.println("Hmmm... \n");
    }

    @After("execution(* sellSquishee(..))")
    public void sayGoodBye() {
        System.out.println("Good Bye! \n");
    }

    @Around("execution(* sellSquishee(..))")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(("Hi! \n"));
        Object retVal = pjp.proceed();
        System.out.println(("See you! \n"));
        return retVal;
    }
}