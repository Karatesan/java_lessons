package org.example.model;

import org.example.Builder;
import org.example.annotations.ImportantString;
import org.example.annotations.LogExecutionTime;
import org.example.annotations.MyAnnotation;
import org.example.annotations.RunImmidiately;

@MyAnnotation
@Builder
public class Car {

    @ImportantString
    public String model;
    private String marka;

    public Car(String model, String marka) {
        this.model = model;
        this.marka = marka;
    }

    @RunImmidiately(times = 3)
    public void startEngine(){
        System.out.println("Wrrmmmm!");
    }

    public void stopEngine(){
        System.out.println("Shhh.");
    }

    @LogExecutionTime
    public void longMethod(){
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Zrobione!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
