public class isEvenCondition implements numberCondition {
    @Override
    public boolean checkCondition(int i) {
        return i % 2 == 0;
    }
}