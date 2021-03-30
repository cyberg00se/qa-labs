package lab2.src.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

public class FileCriticalManager implements ICriticalManager {
    private static List<Class<? extends Exception>> criticalList = new LinkedList<>();

    @Override
    public void ExceptionList(String file)
            throws IOException, ClassNotFoundException
    {
        List<Class<? extends Exception>> tempList=new LinkedList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tempList.add((Class<? extends Exception>) Class.forName(line));
        }

        reader.close();
        criticalList=tempList;
    }

    @Override
    public boolean isCritical(Exception input){
        for (Class<? extends Exception> critClass : criticalList) {
            if (critClass.isInstance(input))
                return true;
        }
        return false;
    }
}
