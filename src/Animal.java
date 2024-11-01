public interface Animal {
    void makeSound();   //抽象方法

    default void sleep(){   //默认方法
        System.out.println("zzz...");
        logsleep();
    }

    static void staticMethod(){     //静态方法
        System.out.println("Static method in interface");
    }

    private void logsleep(){    //私有方法
        System.out.println("logging sleep");
    }
}
