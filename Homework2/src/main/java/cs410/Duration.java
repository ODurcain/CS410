/*
 Owen Durkin
 CS410
 */

package cs410;
/*
 Represents a duration measured in either seconds, minutes, hours, or cumulative seconds.

 Responsibilities - convert hours, minutes, and seconds to cumulative seconds,
 add Duration objects together,
 and formats them to a string representation of H:MM:SS

  */
public class Duration {

    /*
    Stores hours, minutes, seconds, or converted seconds as integers
     */
    int hours, minutes, seconds, convertedSeconds;
    /*
    Stores the formatted string received from the current duration object
     */

    /*
    Constructor for the Duration object to instantiate the variables

    Note: These variables have no constraint on value
     */
    public Duration(int hours, int minutes, int seconds){
        minutes += seconds / 60;
        seconds %= 60;

        hours += minutes / 60;
        minutes %= 60;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /*
    This constructor is for the case of returning the converted seconds
    of hours, minutes, and seconds. It forces the object to technically only return
    one variable. It always returns 0:00:xx when called.
     */
/*    public Duration (int convertedSeconds){
        this(0, 0, convertedSeconds);
    }*/

    /*
    Method to convert a large number of seconds to hours, minutes, seconds.

    Example: 66045 = 18:20:45
     */
    public static Duration of(int startSeconds){
        int hours = (startSeconds/3600);
        int minutes = (startSeconds % 3600) / 60;
        int seconds = startSeconds % 60;
        return new Duration(hours, minutes, seconds);
    }

    /*
    Takes, hours, minutes, and seconds to create a Duration object
     */
    public static Duration of(int hours, int minutes, int seconds){
        return new Duration(hours, minutes, seconds);
    }

    /*
    Adds two Duration objects together and outputs them as a new Duration object

    Example: 66045 Seconds + (60 hours, 61 minutes, 0 seconds) = (79 hours, 21 minutes, 45 seconds)
     */

    public Duration add(Duration other){
        return new Duration(this.hours + other.hours,
                this.minutes + other.minutes,
                this.seconds + other.seconds);
    }

    /*
    Converts a Duration object of hours, minutes, and seconds into just seconds

    Example: (60 hours, 61 minute, 0 seconds) = 0:00:219660 seconds
     */

    public int seconds(){
        this.convertedSeconds = this.hours * 3600 + this.minutes * 60 + this.seconds;
        return this.convertedSeconds;
    }

    /*
    toString override function that represents the duration.

    Example: Receives hours = 0, minutes = 80, seconds = 12
    will output a string 1:20:12
     */

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Duration other = (Duration) obj;

        return this.hours == other.hours &&
                this.minutes == other.minutes &&
                this.seconds == other.seconds;
    }

/*
    Main method to test functions
     */

    public static void main(String[] args) {
        Duration duration1 = Duration.of(66045); // hours, minutes, 45 seconds
        Duration duration2 = Duration.of(1800); // 30 minutes
        Duration duration3 = Duration.of(60, 61, 0);
        Duration durationx = Duration.of(3661); // 1 hour, 1 minute, 1 second
        Duration durationy = Duration.of(9, 99, 99); // 10 hours, 40 minutes, 39 seconds

        Duration sum = duration1.add(duration3);
        Duration sum1 = duration1.add(duration2);
        Duration sumxy = durationx.add(durationy);

        System.out.println(sum);
        System.out.println(sum1);
        System.out.println(sumxy);
        System.out.println(duration1);
        System.out.println(duration3.seconds());
        System.out.println(duration3);
    }
}
