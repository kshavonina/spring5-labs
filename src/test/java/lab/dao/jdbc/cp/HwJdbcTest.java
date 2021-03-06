package lab.dao.jdbc.cp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static common.TestUtils.fromSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class HwJdbcTest {

    @Test
    void name() throws IOException {
        assertThat(
                fromSystemOut((new HwJdbc())::printFirstName),
                is("first_name = Jose\r\n"));
    }
}