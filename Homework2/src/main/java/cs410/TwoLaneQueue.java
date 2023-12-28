/*
 Owen Durkin
 CS410
 */

package cs410;

/*
Import of LinkedList Java package to manipulate queues with.
Import of java.util.NoSuchElementException to throw error if both lanes empty
 */

import java.util.*;

/*
Represents a queue with two lanes. One fast and one slow

Responsibilities - Enqueues elements into the fast lane, enqueues elements into the slow lane,
                   dequeues from both lanes
 */

public class TwoLaneQueue {

    private int i;
    private final Queue<String> enqueueFast;
    private final Queue<String> enqueueSlow;


    public TwoLaneQueue(){
        enqueueFast = new LinkedList<>();
        enqueueSlow = new LinkedList<>();
        i = 0;
    }

    /*
    Array list for the fast queue. Adds x to the end of the array list
     */

    public void enqueueFast(String element){
        enqueueFast.add(element);
    }

    /*
    Array list for the slow queue. Adds y to the end of the array list
     */

    public void enqueueSlow(String element){
        enqueueSlow.add(element);
    }

    /*
    Function to dequeue from both lanes. Throws error if both lanes are empty

    Example: Queues at start
             Fast queue = 'xxxx'
             Slow queue = 'yy'

             Queues after
             Fast queue = ''
             Slow queue ='y'

     */
    public static class FastLaneEmptyException extends Exception {
        public FastLaneEmptyException(String message) {
            super(message);
        }
    }
    public String dequeue(){
        if (!enqueueFast.isEmpty() && i < 3) {
                i+=1;
                return enqueueFast.remove();
            }
        if (i == 3 && !enqueueSlow.isEmpty() ||
                enqueueFast.isEmpty() && !enqueueSlow.isEmpty()){
                i = 0;
                return enqueueSlow.remove();
            }
/*        else if (!enqueueFast.isEmpty()) {
            i++;
            return enqueueFast.remove(0);
        }*/
/*        else if (enqueueFast.isEmpty() && !enqueueSlow.isEmpty()){
            try {
                throw new FastLaneEmptyException("");
            } catch (FastLaneEmptyException e) {
                throw new RuntimeException(e);
                if (RuntimeException(e)){

                }
            }

        }*/
        throw new NoSuchElementException("Both lanes are empty");
    }

    /*
    Main for testing
     */

    public static void main(String[] args) {

        TwoLaneQueue queue = new TwoLaneQueue();
        int j = 0, k  = 0;
        while (j < 4){
            j++;
            queue.enqueueFast("x");
            queue.enqueueFast("x");
            queue.enqueueSlow("y");
        }
        while (k < 12){
            k++;
            queue.dequeue();
        }


        System.out.println("enqueueFast after dequeue: " + queue.getEnqueueFast());
        System.out.println("enqueueSlow after dequeue: " + queue.getEnqueueSlow());
    }

    public Queue<String> getEnqueueFast() {
        return enqueueFast;
    }

    public Queue<String> getEnqueueSlow() {
        return enqueueSlow;
    }
}