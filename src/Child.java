class Child extends Parent{
    static {
        System.out.println("Chile static block");
    }
    static int childStaticVar=20;
    Child(){
        System.out.println("Child constructor");
    }
}