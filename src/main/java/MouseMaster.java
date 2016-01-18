/**
 * Created by tarekray on 9/25/2015.
 */

public class MouseMaster extends  Master {

    public MouseMaster(Room room) {
        super(room);
        this.type = "mouse" ;
        animals = new Mouse[Settings.numberOfMice] ;
        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Mouse(this);
        }
    }



}
