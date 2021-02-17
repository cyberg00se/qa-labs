package lab2.src;

public class ServerConnectionManager implements IServerConnection {

    private int errorCounter;

    public ServerConnectionManager(){
        errorCounter=0;
    }
    @Override
    public int getErrorCounter() {
        
        return errorCounter;
    }

    @Override
    public void SendToServer(Exception error) {
        
        try{

        }
        catch(Exception e){
            errorCounter++;
        }

    }
    
}
