/*
 Owen Durkin
 CS410
 */

package cs410;

/*
Import Java's math library to perform functions
 */

import java.lang.Math;

/*
Represents a rectangle derived from 2 opposing points

Responsibilities - Create a rectangle, determine if a point is inside a rectangle,
check if two rectangles are overlapping
 */

public class Rectangle {

    /*
    Variables representing the two points of a rectangle
     */

    final int x1, y1, x2, y2;

    /*
    Constructor to create the rectangle object and instantiate variables

    Note: variables can be anywhere on a 2d plane and can be positive or negative
     */

    private Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /*
    Method that creates a Rectangle object
     */

    public static Rectangle of(int x1, int y1, int x2, int y2){
        return new Rectangle(x1, y1, x2, y2);
    }

    /*
    Method that finds the area of a Rectangle object from two given points.

    Example: (x1=2, y1=3, x2=23, y2=37) = 714
     */

    public int area(){
        int areaRectangle = ((this.x2-this.x1) * (this.y2-this.y1));
        return Math.abs(areaRectangle);
    }

    /*
    Function that checks if a point lies within a Rectangle object based on boolean comparisons

    Example: Rectangle - (x1=2, y1=3, x2=23, y2=37)
             Point - (7, 28)
             Returns the point is inside
     */

    public boolean contains(int x, int y) {
        return x >= Math.min(x1, x2) && x <= Math.max(x1, x2) &&
                y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
    }

    /*
    Function that checks if two rectangles overlap

    Example: Rectangle1 - (x1=2, y1=3, x2=23, y2=37)
             Rectangle2 - (x1=9, y1=90, x2=23, y2=450)
             Returns rectangles do not overlap
     */

    public boolean overlaps(Rectangle other){
        boolean xOverlap = this.x1 <= other.x2 && other.x1 <= this.x2;
        boolean yOverlap = this.y1 <= other.y2 && other.y1 <= this.y2;
        return xOverlap && yOverlap;
    }

    /*
    Main for testing functions
     */

    public static void main(String[] args) {
        Rectangle rectangle1 = Rectangle.of(2, 3, 23, 37);
        Rectangle rectangle2 = Rectangle.of(9, 90, 23, 450);
        int x = 7, y = 28;
        int area = rectangle1.area();
        if (rectangle1.contains(x, y)){
            System.out.println("Point is inside");
        }
        else{
            System.out.println("Point is outside");
        }
        if (rectangle1.overlaps(rectangle2)) {
            System.out.println("Rectangles overlap");
        } else {
            System.out.println("Rectangles do not overlap");
        }
        System.out.println("The area of the rectangle is: " + area);
    }
}
