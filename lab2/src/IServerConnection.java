package lab2.src;

public interface IServerConnection {

    public int getErrorCounter();
    public void SendToServer(String error);
}
