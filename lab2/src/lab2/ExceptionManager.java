package lab2;

import lab2.src.*;

public class ExceptionManager {
    private int critCounter;
    private int usualCounter;
    private ICriticalManager crManager;
    private IServerConnection serverConManager;

    public ExceptionManager(String file) {
        critCounter = 0;
        usualCounter = 0;

        crManager=new FileCriticalManager();
        serverConManager= ServerManagerFactory.Create();
    }

    public ExceptionManager(ICriticalManager mng, String file) {
        critCounter = 0;
        usualCounter = 0;

        crManager=mng;
        serverConManager=ServerManagerFactory.Create();
    }

    public ICriticalManager getCriticalManager(){
        return crManager;
    }
    public void setCriticalManager(ICriticalManager mng){
        crManager=mng;
    }

    public int getCritCounter() {
        return critCounter;
    }
    public int getUsualCounter() {
        return usualCounter;
    }

    public boolean isCriticalException(Exception input) {
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
}
