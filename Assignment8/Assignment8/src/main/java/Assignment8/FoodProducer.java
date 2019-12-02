package Assignment8;
import java.util.Random;

public class FoodProducer extends Thread {
    FoodBank bank;

    public FoodProducer(FoodBank bank)  {
        this.bank = bank;
    }

    @Override
    public synchronized void run() {

        Random random = new Random();
        while(true) {
        
        int randomNumber = random.nextInt(100) + 1;
        this.bank.giveFood(randomNumber);
        System.out.println("Adding " +  randomNumber +  " items of food, the balance is now " +  this.bank.food +  " items");
        try {
            Thread.sleep(100);
          }
          catch(InterruptedException e) {
            continue;
          }
        }
        
    }
}