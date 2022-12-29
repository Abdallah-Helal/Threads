import java.io.*;
import java.util.logging.*;
public class Producer extends Thread {
    Buffer buf;
    public Producer(Buffer buf) {
        this.buf = buf;
    }
    
    @Override
    public void run() {
        for(int i=0;i<buf.getPrimesCnt();i++)  {
            buf.produce();     
        }
    }
    
}
