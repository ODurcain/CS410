package cs410;

import static org.junit.jupiter.api.Assertions.*;

class BitvectorTest {

    @org.junit.jupiter.api.Test
    void of() {
        // Test actual representation
        // Example: the bit sequence 1101 is represented with length = 4, bits = 0b1011
        assertEquals(new Bitvector(4, 0b1011), Bitvector.of("1101"));
    }

    @org.junit.jupiter.api.Test
    void isSet() {
        //given 1101 and index 1, returns true
        assertEquals(true, Bitvector.of("1101").isSet(1));

        //given 1101 and index 2, returns false
        assertEquals(false, Bitvector.of("1101").isSet(2));
    }

    @org.junit.jupiter.api.Test
    void and() {
        // given 1101 and 0101, returns 0101
        assertEquals(Bitvector.of("0101"),
                Bitvector.of("1101").and(Bitvector.of("0101")));

        // given 1101 and 0011, returns 0001

        // given 1101 and 00, throws
        assertThrows(IllegalArgumentException.class,
                () -> Bitvector.of("1101").and(Bitvector.of("00")));
    }

    @org.junit.jupiter.api.Test
    void append() {
    }

    @org.junit.jupiter.api.Test
    void reverse() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertNotEquals(Bitvector.of("1100"), Bitvector.of("0000"));
        assertNotEquals(Bitvector.of(""), Bitvector.of("0"));
    }
}