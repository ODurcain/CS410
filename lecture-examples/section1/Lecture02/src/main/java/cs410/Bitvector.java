package cs410;

/*
Represents a sequence of bits, up to and including length 64.

Responsibilities:
- length, convert to string, extend and subsequence, concatenate, equality, reverse,
  popcount,  bitwise AND, OR, NOT, indexing
  MAYBE: generate random sequence
 */

public class Bitvector {

    private final int length; // must be in [0 .. 64]

    /*
    Stores the contents of the sequence using the binary representation of the integer
    Stores the first bit (index 0) at the least significant position in the number

    The bit sequence [b0, b1, b2, ... bN-1]
    is represented with bits = (b0 << 0) + (b1 << 1) + (b2 << 2) ... + (bN-1 << (N-1))

    Example: the bit sequence [1,1,0,1] is represented with length = 4, bits = 0b1011 = 11
     */
    private final long bits;

    public Bitvector(int length, long bits) {
        if (! (0 <= length && length <= 64)) { throw new IllegalArgumentException("bad length"); }
        if ((bits >>> length) != 0) { throw new IllegalArgumentException("bits set outside valid range"); }
        this.length = length;
        this.bits = bits;
    }

    // Bitvector(boolean[] bits);

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
    /*
    Check whether the bit at the given index is set (1).
    index must be within [0 .. length-1], otherwise throws ???

    given 1101, index 3, should return true
    given 1101, index 2, should return false
    given 1101, index 5, should throw exception
    */
    public boolean isSet(int index) {
        if (0 <= index && index < length) {
            return ((1L << index) & bits) != 0;
        } else {
            throw new IndexOutOfBoundsException("bad index");
        }
    }

    /*
    Returns a new bitvector consisting of this bitvector's bits followed by a's bits
    If the combined lengths of the bitvectors exceed 64, then throws ??Exception

    given this = 1101, addToEnd = 0010, should return 11010010
    given this = 1101, addToEnd = 00, should return 110100
    given this = 0101, addToEnd = 11000, should return 010111000
    given this = 1101, addToEnd = empty, should return 1101
    */
    public Bitvector append(Bitvector addToEnd) {
        if (this.length + addToEnd.length <= 64) {
            return new Bitvector(this.length + addToEnd.length, this.bits + addToEnd.bits << this.length);
        } else {
            throw new IllegalArgumentException("combined length too long");
        }
    }

    /*
    Returns a new bitvector whose bit at each position is the logical AND of the corresponding
    bits of the given bitvectors.

    this and other must have the same length, otherwise an ??Exception is thrown

    given this = empty, other = empty, should return empty
    given this = 1101, other = 0101, should return 0101
    given this = 1101, other = 0011, should return 0001
     */
    public Bitvector and(Bitvector other) {
        if (this.length == other.length) {
            return new Bitvector(this.length, this.bits & other.bits);
        } else {
            throw new IllegalArgumentException("length mismatch");
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
