package common;

import com.sun.scenario.effect.impl.prism.ps.PPSSepiaTonePeer;
import lab.model.simple.UsualPerson;
import lombok.SneakyThrows;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;

public interface TestUtils {

    @SneakyThrows
    static String fromSystemOut(Runnable runnable) throws IOException {

        PrintStream realOut = System.out;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {

            System.setOut(printStream);
            runnable.run();

            return new String(out.toByteArray());

        } finally {
            System.setOut(realOut);
        }
    }

    /**
     * @apiNote This method use some dirty hack in reflection API for making access to private field!
     */
    @SneakyThrows
    static void setValue2Field(Object o, String name, Object broke) throws NoSuchFieldException, IllegalAccessException {
        assert o.getClass() == UsualPerson.class;
        Field brokeField = o.getClass().getDeclaredField(name);
//        if (!brokeField.canAccess(person))
            brokeField.setAccessible(true);
        brokeField.set(o, broke);
    }
}
