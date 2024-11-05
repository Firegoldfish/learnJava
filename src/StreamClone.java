import java.io.*;

public class StreamClone implements Serializable {
    private String field1;
    private StreamNestedClass nestedObject;
    public StreamClone deepCopy(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (StreamClone) objectInputStream.readObject();
        }
        catch (IOException|ClassNotFoundException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
