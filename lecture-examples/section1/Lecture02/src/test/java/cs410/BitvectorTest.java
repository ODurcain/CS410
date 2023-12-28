package cs410;

import static org.junit.jupiter.api.Assertions.*;

class BitvectorTest {

    @org.junit.jupiter.api.Test
    void of() {
    }

    @org.junit.jupiter.api.Test
    void isSet() {
        // given 1101, index 3, should return true
        assertEquals(true, Bitvector.of("1101").isSet(3));
        // given 1101, index 2, should return false
        // given 1101, index 5, should throw exception

    }

    @org.junit.jupiter.api.Test
    void append() {
    }

    @org.junit.jupiter.api.Test
    void and() {
        // given this = empty, other = empty, should return empty
        // given this = 1101, other = 0101, should return 0101
        assertEquals(Bitvector.of("0101"),
                Bitvector.of("1101").and(Bitvector.of("0101")));
        // given this = 1101, other = 0011, should return 0001
        // given this = 1101, other = 00, should throw
        assertThrows(IllegalArgumentException.class,
                (() -> Bitvector.of("1101").and(Bitvector.of("00"))));
    }

    @org.junit.jupiter.api.Test
    void reverse() {
    }
}