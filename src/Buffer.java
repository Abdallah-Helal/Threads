import java.util.*;
import java.io.*;
import java.util.logging.*;

public class Buffer {
    private  int bufferSize=5, N=20;
    private  Queue<Integer> queue = new LinkedList<>();
    private  LinkedList<Integer> primes = new LinkedList<>();

    semaphore spaces = new semaphore(bufferSize);
    semaphore elements = new semaphore(0);
    
    private int inptr=0;

    //parameterized constructor
    public Buffer(int n, int bufferSize){
        N = n;
        this.bufferSize = bufferSize;
        
        this.generatePrimes();
        
    }
    public void generatePrimes(){
        int[] arr = new int[N];
        Arrays.fill(arr,1);

        for (int i =2; i*i < N; i++) {
            if(arr[i]==1){
                for (int j =i*i; j < N; j+=i) {
                    arr[j]=0;
                }
            }
        }
        for( int i=1;i<N;i++){
            if(arr[i]==1){
                primes.add(i);
            }
        }
        
    }
    public void produce() {
        spaces.P();     //if there is atleast a place in a queue to push
        queue.add(primes.get(inptr));
        //System.out.println("Producer");   System.out.println(primes.get(inptr));
        inptr++;
        elements.V();   // increase the number of elements in the queue
    }
    public  int consume(){
        elements.P();   // if there is atleast an elements in a queue to pull
        int value=queue.remove();
        //System.out.print("Consumer");   System.out.println(primes.get(inptr));
        spaces.V();     // decrease the number of elements in the queue
        return value;
    }
    public int getPrimesCnt(){
        return primes.size();
    }
    public int getBigestPrime(){
        return primes.getLast();
    }
}
