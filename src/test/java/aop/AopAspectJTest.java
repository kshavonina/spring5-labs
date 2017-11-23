package aop;

import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static aop.TestUtils.fromSystemOut;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:ioc.xml")
class AopAspectJTest {

	@Autowired
    private Bar bar;
    
	@Autowired
    private Customer customer;

	private String sout;

    @BeforeEach
    void setUp() throws Exception {
        sout = fromSystemOut(() -> bar.sellSquishee(customer));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue("Before advice is not good enought...", sout.contains("Hello"));
        assertTrue("Before advice is not good enought...", sout.contains("How are you doing?"));
    }

    @Test
    void testAfterAdvice() {
        assertTrue("After advice is not good enought...", sout.contains("Good Bye!"));
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue("Customer is broken", sout.contains("Good Enough?"));
    }

    @Test
    void testAroundAdvice() {
        assertTrue("Around advice is not good enought...", sout.contains("Hi!"));
        assertTrue("Around advice is not good enought...", sout.contains("See you!"));
    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar);
    }
}