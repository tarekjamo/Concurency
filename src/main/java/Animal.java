/**
 * Created by tarekray on 9/25/2015.
 */
public class Animal extends  Thread {
    public static int index = 0 ;
    public int id ;
    public String type ;
    public int localCount ;
    public Master master ;
    public Animal(Master master) {
        this.id = index ;
        index++ ;
        this.master = master ;
    }

    public void run() {
        boolean abort = ! master.turn.equals(this.type)  ;
        while(master.roundCount.value()<master.roundCountMax && !abort) {

            if(master.turn.equals(this.type)  && master.roundCount.value()<master.roundCountMax) {
               if( getRoundLock()) {
                   abort = !master.room.enter(this);
                   if (!abort) {
                       master.room.leave(this);
                   }
                   releaseRoundLock();
               }
               }
        }
    }

    private void releaseRoundLock() {
        master.roundLock.release();
    }

    private boolean getRoundLock() {

         return   master.roundLock.tryAcquire();

    }
}
