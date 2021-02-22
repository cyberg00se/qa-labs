package main;

public class ServerManagerFactory {

    private static IServerConnection crManager=null;

    public static IServerConnection Create(){
        if (crManager!=null)
            return crManager;
        return new ServerConnectionManager();
    }

    public void SetManager(IServerConnection mng){
        crManager=mng;
    }
}
