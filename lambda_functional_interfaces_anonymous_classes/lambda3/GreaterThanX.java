public class GreaterThanX implements NumbCondition{
    int x;

    GreaterThanX(int x){
        this.x = x;
    }
    @Override
    public boolean checkCondition(int i) {
        return i>x;
    }
}
