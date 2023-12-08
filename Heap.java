import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Heap {
    private Node[] heap;
    public int size=0;
    private LocalDateTime currentTime;
    public class Node{
	    Passenger passenger;
	    Node(Passenger passenger){
	        this.passenger=passenger;
	    }
        public int compareTo(Node node2){
            return (node2.passenger.boardingCategory-passenger.boardingCategory);
        }
	}
    public Heap(LocalDateTime currentTime) {
        this.heap = new Node[20];
        this.currentTime=currentTime;
    }
    public Node[] getHeap(){
        return Arrays.copyOf(heap,20);
    }
    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }
    private int parent(int index){
        return (index-1)/2 ;
    }
    private void swap(int index1, int index2){
        Node temp = heap[index1];
        heap[index1]=heap[index2];
        heap[index2]=temp;
    }
    public void insert(Passenger passenger){
        heap[size]=new Node(passenger);
        int current=size;
        while(current>0 &&  heap[current].passenger.boardingCategory < heap[parent(current)].passenger.boardingCategory){
            swap(parent(current),current);
            current= parent(current);
        }
        size++;
    }
    public Passenger remove(){
        if(size==0){
            return null;
        }
        Node minVal=heap[0];
        heap[0]=heap[size-1];
        heap[size-1]=null;
        sinkDown(0);
        size--;
        return minVal.passenger;
    }

    public void sinkDown(int index){
        int minIndex= index;
        while(true){
            int leftIndex= leftChild(minIndex);
            int rightIndex= rightChild(minIndex);
            if(leftIndex>20|| rightIndex>20){
                return;
            }
            if(heap[leftIndex]!=null && heap[leftIndex].compareTo(heap[minIndex])>0){
                minIndex=leftIndex;
            }
            if(heap[rightIndex]!=null && heap[rightIndex].compareTo(heap[minIndex])>0){
                minIndex=rightIndex;
            }
            if(minIndex!=index){
                swap(index, minIndex);
                index=minIndex;
            }else{
                return;
            }
        }
    }
    public ArrayList<Passenger> manageDequeueTimes(LocalDateTime boardingTime){ 
        ArrayList<Passenger> boardedPassengers=new ArrayList<>();
        boolean isEmpty=false;
        Passenger temp;
        while(currentTime.compareTo(boardingTime)<0 && isEmpty==false) { //while the current time is not yet at the present boarding time we keep boarding ppl
            temp=this.remove();
            if(temp!=null){
                if(temp.preAttendedTime.plusSeconds(20).compareTo(currentTime)>0){
                    currentTime=temp.preAttendedTime.plusSeconds(20);
                }else{
                    currentTime=currentTime.plusSeconds(20);
                }
                temp.attendedTime=currentTime;
                boardedPassengers.add(temp);
            }else{
                isEmpty=true;
            }
        }
        return  boardedPassengers;
    }
    
}
