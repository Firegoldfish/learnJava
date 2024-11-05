public class HandClone {
    private String field1;

    public void setNestedObject(HandNestedClass nestedObject) {
        this.nestedObject = nestedObject;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    private HandNestedClass nestedObject;
    private HandClone deepCopy(){
        HandClone copy = new HandClone();
        copy.setField1(this.field1);
        copy.setNestedObject(this.nestedObject.deepCopy());
        return copy;
    }
}
