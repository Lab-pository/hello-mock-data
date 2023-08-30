package asap.hellomockdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class LocalDateTimeTest {

    @Test
    void discardTest() {
        final LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"))
            .withNano(0)
            .withSecond(0).minusDays(1);

        assertThat(localDateTime.getSecond()).isZero();
        assertThat(localDateTime.getNano()).isZero();
    }
}
