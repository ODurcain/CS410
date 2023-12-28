package cs410;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class TwoLaneQueueTest {

    @org.junit.jupiter.api.Test
    void enqueueFast() {
        TwoLaneQueue queue = new TwoLaneQueue();
        queue.enqueueFast("Owen");

        /*
        Given a string "Owen", it should be contained within the fast lane
         */
        assertTrue(queue.getEnqueueFast().contains("Owen"));
        assertFalse(queue.getEnqueueSlow().contains("Durkin"));
    }

    @org.junit.jupiter.api.Test
    void enqueueSlow() {
        TwoLaneQueue queue = new TwoLaneQueue();
        queue.enqueueSlow("Durkin");

        /*
        Given a string "Durkin", it should be contained within the slow lane
         */
        assertTrue(queue.getEnqueueSlow().contains("Durkin"));
        assertFalse(queue.getEnqueueSlow().contains("Owen"));
    }

    @org.junit.jupiter.api.Test
    void dequeue() {
        TwoLaneQueue queue = new TwoLaneQueue();

        queue.enqueueFast("x");
        queue.enqueueFast("x");
        queue.enqueueFast("x");
        queue.enqueueFast("x");
        queue.enqueueSlow("y");
        queue.enqueueSlow("y");

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        /*
        Given elements for fast lane [x,x,x,x] and slow lane [y,y],
        should return slow queue not empty and fast lane empty after 4 dequeue operations
         */
        assertFalse(queue.getEnqueueSlow().isEmpty());
        assertTrue(queue.getEnqueueFast().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void dequeueError(){
            /*
        Queues not given anything should return true if exception caught before filling queue
         */
        TwoLaneQueue queue1 = new TwoLaneQueue();
        assertThrows(NoSuchElementException.class, queue1::dequeue);
    }
}