public class semaphore {
    protected int value = 0 ;
    public semaphore(int initial) { value = initial ; }

    public synchronized void P() {
        value-- ;
        if (value < 0)
            try { 
                wait() ; 
            } 
            catch( InterruptedException e ) {
                System.out.print(e);
            }
    }

    public synchronized void V() {
        value++ ; 
        if (value <= 0) notify() ;
    }
}