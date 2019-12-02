package Assignment8;
public class FoodBankPatrons{
    public static void main(String[] args){
        //do you want us to do getter and setter for food instance variable?? 
        
        //check to see if i did threading correctly 
            FoodBank food = new FoodBank();
            FoodProducer producer = new FoodProducer(food);
            FoodConsumer consumer = new FoodConsumer(food);
            producer.start();
            consumer.start();
    
    }
}