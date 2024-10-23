public class Runner {

    public static boolean isEven(int x){
        return x%2==0;
    }

    public static boolean isBiggerThan20(int x){
        return x>20;
    }

    public static void print(NumbCondition condition){
        for(int i =0;i<50;++i){
            if(condition.checkCondition(i))
                System.out.println(i);
        }
    }

    public static void main(String[] args){

        //print(new GreaterThanX(25));
        //NumbCondition t = new GreaterThanX(30);

        //anonymous class
        NumbCondition impl = new NumbCondition(){
            @Override
            public boolean checkCondition(int x){
                return x%2!=0;
            }
        };

        print(impl);

//        print(new NumbCondition(){
//            @Override
//            public boolean checkCondition(int x){
//                return x%2!=0;
//            }
//        });

        //Lambda
        print(( int x )->{ return x%2!=0; });
        print((int x) -> x%2!=0);
        print(x -> x%2!=0);

        print(Runner::isEven);

    }
}
