import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by tarekray on 9/25/2015.
 */
public class Room {

    public Semaphore turn = new Semaphore(1, true);

    private  Semaphore availablePlace ;

    public SynchronizedCounter totalEntrance ;
    List<Animal> space = new ArrayList<Animal>() ;

    public void simulate() {
        availablePlace = new Semaphore(Settings.roomCapacity,true);
        totalEntrance = new SynchronizedCounter();

        CatMaster catMaster = new CatMaster(this) ;
        MouseMaster mouseMaster = new MouseMaster(this) ;

        catMaster.start() ;
        mouseMaster.start() ;


        while(totalEntrance.value()<Settings.totalEntrance) {

        }

        try {
            catMaster.join() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mouseMaster.join() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    public  boolean enter(Animal animal) {
        getLock();
        if((totalEntrance.value()<Settings.totalEntrance)) {
            space.add(animal);
            animal.localCount++;
            animal.master.roundCount.increment();
            totalEntrance.increment();
            System.out.println(animal.id + animal.type + " entering (Room.java) for "+animal.localCount+"Cumulative " + this.totalEntrance.value());
            return true;
        }
        return false ;
    }
    public boolean leave(Animal animal) {
        if(space.contains(animal)){
            space.remove(animal);
            releaseLock() ;
            return true ;
        }
        return false;


    }

    private  void getLock() {
        try {
            availablePlace.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void releaseLock() {
        availablePlace.release();
    }

}
