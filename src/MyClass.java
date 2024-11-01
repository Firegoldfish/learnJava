public class MyClass {
    static int staticVar = 0;   //静态变量
    static int count=0;

    public MyClass(){
        staticVar++;
    }

    public static void printStaticVar() {
        System.out.println("StaticVar="+staticVar);
    }
    public static void incrementCount(){    //静态方法
        count++;
    }

    public static void displayCount() {
        System.out.println("count="+count);
    }
}
