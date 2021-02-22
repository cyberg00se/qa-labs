package lab2.src.main;

public class ServerConnectionManager implements IServerConnection {

    private int errorCounter;

    public ServerConnectionManager(){
        errorCounter = 0;
    }

    @Override
    public int getErrorCounter() {
        return errorCounter;
    }

    @Override
    public void SendToServer(Exception error) {
        try{
            String exceptionInfo = error.getClass().getName();
            //sending exceptionInfo to server
        }
        catch(Exception e){
            errorCounter++;
        }
    }
}
