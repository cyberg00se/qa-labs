package lab2.src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ExceptionManager // implements ICritical
{

    private static List<Class<? extends Exception>> criticalList = new LinkedList<>();

    private int critCounter;
    private int usualCounter;
    private ICriticalManager crManager;
    private IServerConnection serverConManager;

    public ExceptionManager(String file) throws ClassNotFoundException, IOException {
        critCounter = 0;
        usualCounter = 0;

        crManager=new FileCriticalManager();
        criticalList=crManager.ExceptionList(file);

        serverConManager=ServerManagerFactory.Create();
    }

    public ExceptionManager(ICriticalManager mng, String file) throws ClassNotFoundException, IOException {

        critCounter = 0;
        usualCounter = 0;

        crManager=mng;
        criticalList=mng.ExceptionList(file);

        serverConManager=ServerManagerFactory.Create();
    }

    public ICriticalManager getCriticalManager(){
       return crManager;
    }

    public void setCriticalManager(ICriticalManager mng){
        crManager=mng;
     }

    public boolean isCriticalException(Exception input) {

        //ICriticalManager critManager=new FileCriticalManager();
        return crManager.isCritical(input);
    }

    public void manageException(Exception input) {

        if(isCriticalException(input)){
            serverConManager.SendToServer(input); 
            critCounter++;
        }
            
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
