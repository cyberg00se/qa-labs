package lab2.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileCriticalManager implements ICriticalManager {

    @Override
    public List<Class<? extends Exception>> ExceptionList(String file)
    throws IOException, ClassNotFoundException
    {
        List<Class<? extends Exception>> tempList=new LinkedList<>();

     
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tempList.add((Class<? extends Exception>) Class.forName(line));
        }

        reader.close();
        return tempList;
    }

    @Override
    public boolean isCritical(Exception input){
        /******/
        return false;
    }
}