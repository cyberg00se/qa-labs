package lab2.src;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class ExceptionManager {
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

    public boolean isCritical(Exception input) {
        for (Class<? extends Exception> critClass : criticalList) {
            if (critClass.isInstance(input))
                return true;
        }
        return false;
    }

    public void manageException(Exception input) {
        if(isCritical(input))
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
