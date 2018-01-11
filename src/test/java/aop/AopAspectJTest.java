package aop;

import lab.model.Person;
import lab.model.simple.ApuBar;
import lab.model.Bar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static common.TestUtils.fromSystemOut;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:aop.xml")
class AopAspectJTest {
	@Autowired
    private Bar bar;
    
	@Autowired
    private Person person;

	private String sout;

    @BeforeEach
    void setUp() throws IOException {
        sout = fromSystemOut(() -> bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enough...", sout.contains("Hello"));
        assertTrue("Before advice is not good enough...", sout.contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enough...", sout.contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken", sout.contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enough...", sout.contains("Hi!"));
        assertTrue("Around advice is not good enough...", sout.contains("See you!"));
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar);
    }
}