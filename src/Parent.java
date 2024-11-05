class Parent{
    static {
        System.out.println("Parent static block");
    }
    static int parentStaticVar=10;
    Parent(){
        System.out.println("Parent constuctor");
    }
}