package aop;

import lab.model.Bar;
import lab.model.CustomerBrokenException;
import lab.model.Person;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static common.TestUtils.fromSystemOut;
import static common.TestUtils.setValue2Field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:aop.xml")
class AopAspectJExceptionTest {

    @Autowired
    private Bar bar;

    @Autowired
    private Person person;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        setValue2Field(person,"broke",true);
    }

    @Test
    void testAfterThrowingAdvice() {

        val sout = fromSystemOut(() ->
                assertThrows(CustomerBrokenException.class, () ->
                        bar.sellSquishee(person)));

        //noinspection SpellCheckingInspection
        assertTrue("Customer is not broken ",
                sout.contains("Hmmm..."));
    }

    @AfterEach
    void tearDown() {
        setValue2Field(person,"broke", false);
    }
}