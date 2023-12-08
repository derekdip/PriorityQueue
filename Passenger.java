import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;

public class Passenger {
    //didn't use private variable so accessor methods wouldn't use up a ton of space
    public String firstName;
    public String lastName;
    public LocalDate dOB;
    public LocalDateTime preAttendedTime; //Arrival Time (in the boarding queue)
    public LocalDateTime attendedTime; //dequeued time
    public int boardingCategory;

    public Passenger(String firstName, String lastName, LocalDate dOB, LocalDateTime preAttendedTime, int boardingCategory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dOB = dOB;
        this.preAttendedTime = preAttendedTime;
        this.boardingCategory = boardingCategory;
    }

    @Override
    public String toString() {
        return "Passenger [fName=" + firstName + ", lName=" + lastName + ", dOB=" + dOB + ", preTime="
                + preAttendedTime.toLocalTime() + ", attendedTime=" + attendedTime.toLocalTime() 
                + ", deltaSec=" +   Duration.between(preAttendedTime, attendedTime).getSeconds()  + ", boardingCat=" + boardingCategory+"]";
    }
    
    
    

}
