package Assignment8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class RaceTrack extends Application {
    public static Button start;
    public static Button pause;
    public static Button reset;

    private static ReentrantLock lock = new ReentrantLock();

    public static Image raceCar1;
    public static Image raceCar2;
    public static Image raceCar3;

    public static ImageView car1;
    public static ImageView car2;
    public static ImageView car3;

    boolean isRace = false;
    boolean isFinished = false;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 500, 200);
        
       // x and y position height and width
        Rectangle rect1 = new Rectangle(370,10);
        rect1.setTranslateY(-40);
        rect1.setFill(Color.LIGHTGREY);
        
      
        
        layout.getChildren().add(rect1);

        Rectangle rect2 = new Rectangle(370,10);
        rect2.setTranslateY(0);
        rect2.setFill(Color.LIGHTGREY);
        
        layout.getChildren().add(rect2);

        Rectangle rect3 = new Rectangle(370,10);
        rect3.setTranslateY(40);
        rect3.setFill(Color.LIGHTGREY);
        
        layout.getChildren().add(rect3);

        // Rectangle rect3 = new Rectangle(-150,40,380,10);
    
        // rect3.setFill(Color.LIGHTGREY);
        
        // layout.getChildren().add(rect3);

        raceCar1= new Image(new FileInputStream("src/main/java/Assignment8/sportive-car.png"));
        car1 = new ImageView(raceCar1);
     
        layout.getChildren().add(car1);
        car1.setTranslateX(-200);
        car1.setTranslateY(-40);

        raceCar2= new Image(new FileInputStream("src/main/java/Assignment8/sportive-car.png"));
        car2 = new ImageView(raceCar2);
     
        layout.getChildren().add(car2);
        car2.setTranslateX(-200);
        car2.setTranslateY(0);


        raceCar3= new Image(new FileInputStream("src/main/java/Assignment8/sportive-car.png"));
        car3 = new ImageView(raceCar3);
     
        layout.getChildren().add(car3);
        car3.setTranslateX(-200);
        car3.setTranslateY(40);

        start = new Button();
        start.setText("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isRace && !isFinished){
                    isRace = true; 
                    System.out.println("START");
                }
                
                
                
            }
        });



        layout.getChildren().add(start);
        start.setTranslateX(-100);
        start.setTranslateY(-75);
        
        
        pause = new Button();
        pause.setText("Pause");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isRace = false;
                System.out.println("PAUSE");
                
            }
        });
        layout.getChildren().add(pause);
        pause.setTranslateX(0);
        pause.setTranslateY(-75);

        reset = new Button();
        reset.setText("Reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lock.lock();
                try{
                    isRace = false;
                    isFinished = false;
                    System.out.println("RESET");
                    car1.setTranslateX(-200);
                    car1.setTranslateY(-40);
                    car2.setTranslateX(-200);
                    car2.setTranslateY(0);
                    car3.setTranslateX(-200);
                    car3.setTranslateY(40);
                } finally {
                    lock.unlock();
                }
                
            }
        });
        layout.getChildren().add(reset);
        reset.setTranslateX(100);
        reset.setTranslateY(-75);
        
        
        


        // layout.getChildren().add(pause);
        // layout.getChildren().add(reset);

        new Thread(() -> {
            Random rand = new Random();
            while (true) {
              if (isRace) {
                if(car1.getTranslateX() < 200){

                    int translatedX = rand.nextInt(11);
                    if(car1.getTranslateX() + translatedX >= 200){
                        lock.lock();
                        try {
                            isFinished = true;
                            car1.setTranslateX(200);
                            isRace = false;
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                        // show a joptionpane dialog using showMessageDialog
                        JOptionPane.showMessageDialog(frame,
                            "Car one wins!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                        } finally {
                            lock.unlock();
                        }
                        
                        
                        
                    } else {
                        lock.lock(); 
                        try {
                            car1.setTranslateX(car1.getTranslateX()+ translatedX);
                        } finally{
                            lock.unlock();
                        }
                        
                    }  
                } 
              }
    
              try {
                Thread.sleep(50);
              } catch (Exception e) {
                return; 
              }
            }
          }).start();


          new Thread(() -> {
            Random rand = new Random();
            while (true) {
              if (isRace) {
                if(car2.getTranslateX() < 200){

                    int translatedX = rand.nextInt(11);
                    if(car2.getTranslateX() + translatedX >= 200){
                        lock.lock();
                        try {
                            isFinished = true;
                            car2.setTranslateX(200);
                            isRace = false;
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                        // show a joptionpane dialog using showMessageDialog
                        JOptionPane.showMessageDialog(frame,
                            "Car two wins!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                        } finally {
                            lock.unlock();
                        }
                        
                    } else {
                        lock.lock(); 
                        try {
                            car2.setTranslateX(car2.getTranslateX()+ translatedX);
                        } finally{
                            lock.unlock();
                        }
                    }  
                } 
              }
    
              try {
                Thread.sleep(50);
              } catch (Exception e) {
                return; 
              }
            }
          }).start();



          new Thread(() -> {
            Random rand = new Random();
            while (true) {
              if (isRace) {
                if(car3.getTranslateX() < 200){

                    int translatedX = rand.nextInt(11);
                    if(car3.getTranslateX() + translatedX >= 200){
                        lock.lock();
                        try {
                            isFinished = true;
                            car3.setTranslateX(200);
                            isRace = false;
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                            // show a joptionpane dialog using showMessageDialog
                            JOptionPane.showMessageDialog(frame,
                                "Car three wins!",
                                "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                        } finally {
                            lock.unlock();
                        }
                        
                    } else {
                        lock.lock(); 
                        try {
                            car3.setTranslateX(car3.getTranslateX()+ translatedX);
                        } finally{
                            lock.unlock();
                        }
                    }  
                } 
              }
    
              try {
                Thread.sleep(50);
              } catch (Exception e) {
                return; 
              }
            }
          }).start();

        stage.setTitle("Richmond Raceway");

        stage.setScene(scene);
        stage.show();


       
    }

    public static void main(String[] args){
        launch();
    }
}