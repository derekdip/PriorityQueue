import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class driver {
    public static void readFromFile(ArrayList<Passenger>arrivalOrder, ArrayList<Passenger>boardingOrder,Heap newHeap, LocalDateTime preAttendedArrivalTime){
        try {
            Scanner readInputFile;
            File inputFile = new File("passengers.txt");
            readInputFile = new Scanner(inputFile);
            String line;
            Random randomSeconds= new Random(1);
            while (readInputFile.hasNext()) {
                line = readInputFile.nextLine();
                String[] values = line.split("\s+");
                preAttendedArrivalTime=preAttendedArrivalTime.plusSeconds(randomSeconds.nextInt(25)); //adds between 0-40 seconds of passenger arrival to simulate arrival time in a 0-10 minute window
                Passenger newPassenger=new Passenger(values[0], values[1], LocalDate.parse(values[2]),preAttendedArrivalTime, Integer.parseInt(values[3]));
                arrivalOrder.add(newPassenger);
                boardingOrder.addAll(newHeap.manageDequeueTimes(preAttendedArrivalTime));
                newHeap.insert(newPassenger);

            }
            readInputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        preAttendedArrivalTime=preAttendedArrivalTime.plusSeconds(1000); //added a bit of time so if flight can leave, we're not gonna wait for late Passengers
        boardingOrder.addAll(newHeap.manageDequeueTimes(preAttendedArrivalTime));
    }

    public static void main(String[] args) {
        ArrayList<Passenger> boardingOrder= new ArrayList<>();
        ArrayList<Passenger> arrivalOrder= new ArrayList<>();
        LocalDateTime initialArrivalTime= LocalDateTime.now();//time queue is instantiated
        LocalDateTime preAttendedArrivalTime= LocalDateTime.now();
    
        Heap newHeap= new Heap(initialArrivalTime);
        Scanner userInput= new Scanner(System.in);
        String mainMenuInput;

        do{
            System.out.println("1. Create Passengers with 'passengers.txt'");
            System.out.println("2. Print Passengers in order of arrival");
            System.out.println("3. Print Passengers in order of priority seated");
            System.out.println("4. Search for Passengers");
            System.out.println("5. Exit");
            mainMenuInput=userInput.nextLine();
            switch (mainMenuInput) {
                case "1":
                    System.out.println("-------Reading From File Start---------");
                    readFromFile(arrivalOrder, boardingOrder, newHeap, preAttendedArrivalTime);
                    System.out.println("-------Reading From File End---------");
                    break;
                case "2":
                    Iterator<Passenger> arrivalIt=arrivalOrder.iterator();
                    System.out.println("----------------Start Arrival Order-------------------");
                    while(arrivalIt.hasNext()){
                        System.out.println(arrivalIt.next());
                    }
                    System.out.println("----------------End Arrival Order-------------------");
                    break;
                case "3":
                    Iterator<Passenger> boardingIt=boardingOrder.iterator();
                    System.out.println("----------------Start Order being Boarded-------------------");
                    while(boardingIt.hasNext()){
                        System.out.println(boardingIt.next());
                    }
                    System.out.println("----------------End Order being Boarded-------------------");
                    break;
                case "4":
                    // no implementation of hashmap/search :(
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Please enter a valid value");
                    break;
            }
        }while(!mainMenuInput.contains("5"));
        userInput.close();


    }
}
