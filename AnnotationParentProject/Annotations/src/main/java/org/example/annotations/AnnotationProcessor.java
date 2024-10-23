package org.example.annotations;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void processAnnotations(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();

        // Iterate through all methods of the class
        for (Method method : clazz.getDeclaredMethods()) {

            // Check if the method is annotated with @LogExecutionTime
            if (method.isAnnotationPresent(LogExecutionTime.class)) {

                // Measure execution time
                long startTime = System.currentTimeMillis();

                // Invoke the method
                method.invoke(obj);

                // Calculate execution time
                long endTime = System.currentTimeMillis();
                System.out.println("Execution time of " + method.getName() + ": " + (endTime - startTime) + "ms");
            }
        }
    }
}
