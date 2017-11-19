package ioc;

import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class HelloWorldTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "ioc.xml";

    private BeanFactory context =
            new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);

    @Test
    void testInitPerson() {
        Person person = (UsualPerson) context.getBean("person");
        assertThat(person, is(getExpectedPerson()));
    }

    static Person getExpectedPerson() {
        return new UsualPerson()
                .setAge(35)
                .setName("John Smith")
                .setHeight(1.78f)
                .setProgrammer(true)
                .setContacts(Arrays.asList("asd@asd.ru", "+7-234-456-67-89"))
                .setCountry(new Country()
                        .setId(1)
                        .setName("Russia")
                        .setCodeName("RU"));
    }
}
