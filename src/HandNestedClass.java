public class HandNestedClass {
    public void setNestedField(int nestedField) {
        this.nestedField = nestedField;
    }

    private int nestedField;
    public HandNestedClass deepCopy(){
        HandNestedClass copy = new HandNestedClass();
        copy.setNestedField(this.nestedField);
        return copy;
    }
}
