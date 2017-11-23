package aop;

import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static aop.TestUtils.fromSystemOut;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private Customer customer;
    private String sout;

    @BeforeEach
    void setUp() {
        customer.setBroke(true);
        sout = fromSystemOut(() -> bar.sellSquishee(customer));
    }

    @Test
    void testAfterThrowingAdvice() {
 
    	assertThrows(CustomerBrokenException.class, () ->
                bar.sellSquishee(customer));
    	
        assertTrue("Customer is not broken ", sout.contains("Hmmm..."));
    }
}