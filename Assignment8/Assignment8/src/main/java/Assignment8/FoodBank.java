package Assignment8;
public class FoodBank{
    int food;

    public FoodBank(){
        this.food = 0;
    }


    public void giveFood(int giveFood){
        this.food += giveFood;
    }


    public void takeFood(int takeFood){
        this.food -= takeFood;
    }
}