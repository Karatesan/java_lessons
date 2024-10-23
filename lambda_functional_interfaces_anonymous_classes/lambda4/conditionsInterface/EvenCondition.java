package conditionsInterface;

public class EvenCondition implements NumberCondition{

    @Override
    public boolean checkCondition(int number) {
        return number%2==0;
    }
}
