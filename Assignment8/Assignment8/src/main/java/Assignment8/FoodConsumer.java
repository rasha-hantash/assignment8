package Assignment8;
import java.util.Random;

public class FoodConsumer extends Thread {
    FoodBank bank;

    public FoodConsumer(FoodBank bank)  {
        this.bank = bank;
    }

    @Override
    public synchronized void run() {

        Random random = new Random();
        while(true) {
        
        int randomNumber = random.nextInt(100) + 1;
        

        int acceptState = this.bank.food - randomNumber;
            if(acceptState >=0){
                this.bank.takeFood(randomNumber);
                System.out.println("Taking " +  randomNumber +  " items of food, the balance is now " +  this.bank.food +  " items");
            } else {
                System.out.println("Waiting to get food");
            }
        
        try {
            Thread.sleep(100);
          }
          catch(InterruptedException e) {
            continue;
          }
        }
        
    }
}