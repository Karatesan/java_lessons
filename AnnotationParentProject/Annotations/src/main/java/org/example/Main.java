package org.example;

import org.example.annotations.AnnotationProcessor;
import org.example.annotations.ImportantString;
import org.example.annotations.MyAnnotation;
import org.example.annotations.RunImmidiately;
import org.example.model.Car;
import org.example.model.CarBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        //class level

        Car car = new Car("Fiat","Punto");
        Class<? extends Car> aClass = Car.class;
        Annotation[] annotations = aClass.getAnnotations();
        String value = aClass.getAnnotation(MyAnnotation.class).value();
        System.out.println(value);
        if (aClass.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("Dupa2");
        }



        for (Method method : car.getClass().getDeclaredMethods()){
            if (method.isAnnotationPresent(RunImmidiately.class)) {
                RunImmidiately annotation = method.getAnnotation(RunImmidiately.class);
                for(int i=0;i<annotation.times();++i) {
                    method.invoke(car);
                }
            }
        }
        for (Field declaredField : car.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(ImportantString.class)) {
                System.out.println("Ten field: "+declaredField.getName()+" je wazny");
                Object o = declaredField.get(car);
                if(o instanceof String stringVal){
                    System.out.println(stringVal.toUpperCase());
                }
            }

        }
        
        AnnotationProcessor.processAnnotations(car);


    }
}