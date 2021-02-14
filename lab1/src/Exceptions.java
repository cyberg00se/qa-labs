package lab1.src;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class Exceptions {

    static List<Class<? extends Exception>> criticalList = new LinkedList<>();

    int critCounter;
    int usualCounter;

    public Exceptions(){

        // конструктор
        critCounter = 0;
        usualCounter = 0;

        criticalList.add(NullPointerException.class);
        criticalList.add(ArrayIndexOutOfBoundsException.class);
        criticalList.add(FileNotFoundException.class);
    }
  
    public boolean isCritical(Exception input) {

        for (Class<? extends Exception> critClass : criticalList) {
            if (critClass.isInstance(input)) 
            {
             return true;
            }                
        }
        return false;
            
    }

    public void manageException(Exception input) {
        
        if(isCritical(input)) 
            critCounter++;
        else
            usualCounter++;
        
    }
}
