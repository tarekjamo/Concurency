import java.util.concurrent.Semaphore;

/**
 * Created by tarekray on 9/25/2015.
 */
public class Master extends  Thread {
    public Room room ;
    public Animal[] animals ;
    public String type ;

    public SynchronizedCounter roundCount = new SynchronizedCounter() ;
    public int roundCountMax = Settings.roundCountMax ;
    public Semaphore roundLock = new Semaphore(Settings.roundCountMax ,true) ;

    public String turn =""  ;

    public Master(Room room) {
        this.room = room;
    }

    public void run() {


        while(room.totalEntrance.value()<Settings.totalEntrance) {
            getTurnLock();
            this.turn = this.type ;
            for (int i = 0; i < animals.length; i++) {
                animals[i].start();
            }


            while (roundCount.value() < roundCountMax) {

            }


           for (int i = 0; i < animals.length; i++) {

                if(this.type=="cat")
                    animals[i] = new Cat(this) ;

                if(this.type=="mouse")
                    animals[i] = new Mouse(this) ;
 }
            roundCount = new SynchronizedCounter();

            releaseTurnLock();
        }


    }


    public void releaseTurnLock() {
        room.turn.release();
    }

    public void getTurnLock() {
        try {
            room.turn.acquire();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
