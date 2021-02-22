package main;

public interface IServerConnection {
    public int getErrorCounter();
    public void SendToServer(Exception error);
}
