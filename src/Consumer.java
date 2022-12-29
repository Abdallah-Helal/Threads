import java.io.*;
import java.util.logging.*;
public class Consumer  extends Thread {
    Buffer buf;
    FileWriter out;

    public Consumer(Buffer buf,String  outputFileName)throws IOException  {
        this.buf = buf;
        try {
            out = new FileWriter(outputFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
    }
    @Override
    public void run() {
        for(int i=0;i<buf.getPrimesCnt();i++) {
            int value=buf.consume();
            try{ 
                out.write("\""+Integer.toString(value)+"\"");
                out.write(", ");
            }
            catch (IOException ex){
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);

            };

        } 
        try{ 
            out.close();
        }
        catch (IOException ex){
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);

        };
        
        
    }   
}
