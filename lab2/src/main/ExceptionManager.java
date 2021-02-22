package main;

public class ExceptionManager {
    private int critCounter;
    private int usualCounter;
    private ICriticalManager crManager;
    private IServerConnection serverConManager;

    public ExceptionManager() {
        critCounter = 0;
        usualCounter = 0;

        crManager=new FileCriticalManager();
        serverConManager= ServerManagerFactory.Create();
    }

    public ExceptionManager(ICriticalManager mng) {
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

    public boolean isCritical(Exception input) {
        return crManager.isCritical(input);
    }

    public void manageException(Exception input) {
        if(isCritical(input)){
            serverConManager.SendToServer(input);
            critCounter++;
        }
        else
            usualCounter++;
    }
}
