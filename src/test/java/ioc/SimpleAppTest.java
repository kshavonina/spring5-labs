package ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static ioc.HelloWorldTest.getExpectedPerson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SimpleAppTest {
	
	private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "ioc.xml";

	private BeanFactory context =
            new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);

	@Test
	void testInitPerson() {
		assertThat(context.getBean("person"),
				is(getExpectedPerson()));
	}
}
