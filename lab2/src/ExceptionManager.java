package lab2.src;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class ExceptionManager //implements ICritical 
{

    private static List<Class<? extends Exception>> criticalList = new LinkedList<>();

    private int critCounter;
    private int usualCounter;

    public ExceptionManager(){
        critCounter = 0;
        usualCounter = 0;

        criticalList.add(NullPointerException.class);
        criticalList.add(ArrayIndexOutOfBoundsException.class);
        criticalList.add(FileNotFoundException.class);
    }

    public boolean isCriticalException(Exception input) {

        ICriticalManager critManager=new FileCriticalManager();
        return critManager.isCritical(input);

    }

    public void manageException(Exception input) {
        if(isCriticalException(input))
            critCounter++;
        else
            usualCounter++;
    }

    public int getCritCounter() {
        return critCounter;
    }

    public int getUsualCounter() {
        return usualCounter;
    }
}
