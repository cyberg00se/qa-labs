import java.io.FileNotFoundException;
import java.io.InvalidObjectException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static List<Class<? extends Exception>> criticalList = new LinkedList<>();
    public static void main(String[] args) throws Exception {

        criticalList.add(NullPointerException.class);
        criticalList.add(ArrayIndexOutOfBoundsException.class);
        criticalList.add(FileNotFoundException.class);

        System.out.print(isCritical(new InvalidObjectException("hell")));
        System.out.print(isCritical(new NullPointerException()));
    }

    public static boolean isCritical(Exception input) {

        for (Class<? extends Exception> critClass : criticalList) {
            if (critClass.isInstance(input)) 
            {
             return true;
            }                
        }
        return false;
            
    }
}