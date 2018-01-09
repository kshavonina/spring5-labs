package lab.aop;

import lab.model.Drink;
import lab.model.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Aspect
@Component
public class Politeness {

    @Pointcut("execution(* sellSquishee(..))")
    private void sellSquisheePointCut() {
    }

    @Before("sellSquisheePointCut() && args(person)")
    public void sayHello(Person person) {
        out.printf("Hello %s. How are you doing?%n", person.getName());
    }

    @AfterReturning(pointcut = "sellSquisheePointCut()",
            returning = "drink")
    public void askOpinion(Drink drink) {
        out.printf("Is %s Good Enough?%n", drink.getName());
    }

    @AfterThrowing("sellSquisheePointCut()")
    public void sayYouAreNotAllowed() {
        out.println("Hmmm...");
    }

    @After("sellSquisheePointCut()")
    public void sayGoodBye() {
        out.println("Good Bye!");
    }

    @Around("sellSquisheePointCut()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        out.println(("Hi!"));
        Object retVal = pjp.proceed();
        out.println(("See you!"));
        return retVal;
    }
}