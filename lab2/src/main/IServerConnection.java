package lab2.src.main;

public interface IServerConnection {
    public int getErrorCounter();
    public void SendToServer(Exception error);
}
