/**
 * Created by tarekray on 9/25/2015.
 */
public class CatMaster extends  Master {


    public CatMaster(Room room){
        super(room) ;
        this.type = "cat" ;
        animals = new Cat[Settings.numberOfCats];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Cat(this) ;
        }
    }







}
