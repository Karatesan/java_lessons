package AnonymousClass;

public class Runner {

    public static void main(String args[]){
        //1
        Animal animal = new Cat();
        animal.speak();

        //2        // stworzenie nowej klasy implementującej Animal
        //        // z odpowiednią metodą speak i przypisanie stworzonego
        //        // na jej podstawie obiektu do zmiennej

        Animal animal2 = new Animal(){
            @Override
            public void speak(){
                System.out.println("Anonymous miau");
            }
        };
        animal2.speak();
    }
}
