public class Cloner implements Cloneable{
    private String field1;
    private NestedClass nestedClass;

    @Override
    protected Object clone() throws CloneNotSupportedException{
        Cloner cloner = (Cloner) super.clone();
        cloner.nestedClass = (NestedClass) nestedClass.clone(); //深拷贝的内部引用对象
        return cloner;
    }
}
