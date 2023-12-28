package cs410;

/*
Represents an immutable sequence of bits, with length up to 64 bits.
Responsibilities: bit operations like OR, AND, NOT, isSet, concatenate, convert to string
*/
public class Bitvector {

    private final int length; // must be in [0..64]

    /*
    The first bit in the sequence is represented as the least significant bit of the integer bits

    The bit sequence [b0, b1, b2, ... bN-1]
    is represented as (b0 << 0) + (b1 << 1) + (b2 << 2) ... + (bN-1 << (N-1)).

    Invariant: bits contains no bits set above index length-1.
    Example: the bit sequence 1101 is represented with length = 4, bits = 0b1011
     */
    final long bits;

    public Bitvector(int length, long bits) {
        if (! (0 <= length && length <= 64)) { throw new IllegalArgumentException("bad length"); }
        if ((bits >>> length) != 0) { throw new IllegalArgumentException("bits set outside valid range"); }
        this.length = length;
        this.bits = bits;
    }

    // s must be in [01]*, length <= 64
    public static Bitvector of(String s) {
        if (!(s.length() <= 64)) {
            throw new IllegalArgumentException("string too long");
        }
        long bits = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                bits = (bits << 1) + 0;
            } else if (c == '1') {
                bits = (bits << 1) + 1;
            } else {
                throw new IllegalArgumentException("expected String in [01]*, got " + c);
            }
        }
        return new Bitvector(s.length(), bits).reverse();
    }

    /* Is the bit at the given position in the sequence 1?
      The given index must be between 0 and length-1, otherwise it throws IndexOutOfBoundsException

      given 1101 and index 1, returns true
      given 1101 and index 2, returns false
     */
    public boolean isSet(int index) {
        if (0 <= index && index < length) {
            return ((1L << index) & bits) != 0;
        } else {
            throw new IndexOutOfBoundsException("bad index");
        }
    }

    /*
    Returns a new bitvector consisting of the logical AND of the bits at each position in the two given bitvectors.
    The other bitvector must have the same length as this, otherwise throws IllegalArgumentException

    given 1101 and 0101, returns 0101
    given 1101 and 0011, returns 0001
    given 1101 and 00, throws
     */
    public Bitvector and(Bitvector other) {
        if (this.length == other.length) {
            return new Bitvector(this.length, this.bits & other.bits);
        } else {
            throw new IllegalArgumentException("length mismatch");
        }
    }

    /*
    Concatenate given bitvectors
    Throws ??Exception if the combined length is > 64

    given this = 1101 and addToEnd = 00, returns 110100
     */
    public Bitvector append(Bitvector addToEnd) {
        if (this.length + addToEnd.length <= 64) {
            return new Bitvector(this.length + addToEnd.length, this.bits + addToEnd.bits << this.length);
        } else {
            throw new IllegalArgumentException("combined length too long");
        }
    }

    /* given this = 1101, returns 1011 */
    public Bitvector reverse() {
        long oldbits = this.bits;
        long newbits = 0;
        for (int i = 0; i < this.length; i++) {
            newbits = (newbits << 1) + (oldbits & 1);
            oldbits = (oldbits >>> 1);
        }
        return new Bitvector(this.length, newbits);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Bitvector) {
            Bitvector otherbv = (Bitvector) other;
            return (this.length == otherbv.length && this.bits == otherbv.bits);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < length; i++) {
            out = out + (isSet(i) ? "1" : "0");
        }
        return out;
    }
}
