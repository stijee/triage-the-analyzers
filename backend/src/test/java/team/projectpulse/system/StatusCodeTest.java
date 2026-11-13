package team.projectpulse.system;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

class StatusCodeTest {

    @Test
    void testStatusCodeCannotBeInstantiated() {
        Constructor<?>[] constructors = StatusCode.class.getDeclaredConstructors();

        assertThat(constructors).isNotEmpty();
        for (Constructor<?> constructor : constructors) {
            assertThat(Modifier.isPrivate(constructor.getModifiers()))
                    .as("constructor %s should be private", constructor)
                    .isTrue();
        }
    }

}
