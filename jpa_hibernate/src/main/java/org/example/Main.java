package org.example;

import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import org.example.model.Student;


import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Student s = new Student("Maciek", "m");
            Student s1 = new Student( "Pawel", "m");
            Student s2 = new Student("Franek", "m");
            //persist - save
            em.persist(s);
            em.persist(s1);
            em.persist(s2);

            transaction.commit();
            System.out.println("Student saved successfully!");

            TypedQuery<Student> query = em.createQuery("Select s FROM Student s WHERE s.firstName = :firstName", Student.class);
            query.setParameter("firstName","Maciek");
            Student singleResult = query.getSingleResult();
            System.out.println(singleResult);
            List<Student> list = query.getResultList();
            System.out.println(list);

            //szukamy po primary key
            Student student = em.find(Student.class, 2L);
            System.out.println(student);
            em.remove(student);

        } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();  // Rollback in case of an error
        }
        e.printStackTrace();
    } finally {
        em.close();  // Close EntityManager
        emf.close(); // Close EntityManagerFactory
    }
    }
}
