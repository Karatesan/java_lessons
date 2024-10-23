package conditionsInterface;

public class LessThan50Condition implements  NumberCondition{

    @Override
    public boolean checkCondition(int number) {
        return number <50;
    }
}
