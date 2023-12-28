package cs410;

import static org.junit.jupiter.api.Assertions.*;

class DurationTest {

    @org.junit.jupiter.api.Test
    void of() {
        Duration duration = Duration.of(3661);
        // Given a duration of 3661 seconds, 1 hour, 1 minute, and 1 seconds should be returned
        assertEquals(1, duration.hours);
        assertEquals(1, duration.minutes);
        assertEquals(1, duration.seconds);
    }

    @org.junit.jupiter.api.Test
    void testOf() {
        Duration duration = Duration.of(2, 45, 30);
        // Given a duration of 2 hours, 45 minutes, and 30 seconds, the same should be returned
        assertEquals(2, duration.hours);
        assertEquals(45,duration.minutes);
        assertEquals(30, duration.seconds);
    }

    @org.junit.jupiter.api.Test
    void add() {
        Duration duration1 = Duration.of(3661); // 1 hour, 1 minute, 1 second
        Duration duration2 = Duration.of(1000); // 16 minutes, 40 seconds
        Duration duration3 = Duration.of(9, 99, 99); // 10 hours, 40 minutes, 39 seconds
        Duration duration4 = Duration.of(4, 23, 893); // 4 hours, 39 minutes, 53 seconds

        /* Given either a format of seconds, or hours, minutes, seconds. The sum function should return the expected value
           as H:MM:SS regardless of whether the input durations were seconds + seconds; hours, minutes, seconds + seconds;
           or hours, minute, seconds + hours, minutes, seconds
         */
        Duration sum1 = duration1.add(duration2);
        Duration sum2 = duration1.add(duration3);
        Duration sum3 = duration3.add(duration4);

        assertEquals("1:17:41", sum1.toString());
        assertEquals("11:41:40", sum2.toString());
        assertEquals("15:18:32", sum3.toString());

    }

    @org.junit.jupiter.api.Test
    void seconds() {
        Duration duration = Duration.of(3, 99, 900);
        // Given a duration object of 3 hours, 99 minutes, and 900 seconds, the expected return is 4:54:00
        assertEquals("4:54:00", duration.toString());
    }
}