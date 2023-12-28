package cs410;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @org.junit.jupiter.api.Test
    void of() {
        Rectangle rectangle = Rectangle.of(3, 7, 23, 37);

        // Given x1=3, y1=7, x2=23, y2=37, the return should be the same
        assertEquals(3, rectangle.x1);
        assertEquals(7, rectangle.y1);
        assertEquals(23, rectangle.x2);
        assertEquals(37, rectangle.y2);
    }

    @org.junit.jupiter.api.Test
    void area() {
        Rectangle rectangle = Rectangle.of(3, 7, 23, 37);

        // Given x1=3, y1=7, x2=23, y2=37, the area function should return area=20x30=600
        assertEquals(600, rectangle.area());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        Rectangle rectangle = Rectangle.of(3, 7, 23, 37); // 1 hour, 1 minute, 1 second

        /* Given x1=3, y1=7, x2=23, y2=37, a point (7, 28) should return false
           and a point (25, 76) should return false
         */
        assertTrue(rectangle.contains(7, 28));
        assertFalse(rectangle.contains(25, 76));
    }

    @org.junit.jupiter.api.Test
    void overlap() {
        Rectangle rectangle1 = Rectangle.of(3, 7, 23, 37);
        Rectangle rectangle2 = Rectangle.of(3, 23, 33, 57);
        Rectangle rectangle3 = Rectangle.of(56, 90, 74, 97);

        /*
        Given a rectangle of x1=3, y1=7, x2=23, y2=37, a rectangle x1=3, y1=23, x2=33, y2=57 should return true
        a rectangle x1=56, y1=90, x2=74, y2=97 should return false
         */
        assertTrue(rectangle1.overlaps(rectangle2));
        assertFalse(rectangle1.overlaps(rectangle3));
    }
}