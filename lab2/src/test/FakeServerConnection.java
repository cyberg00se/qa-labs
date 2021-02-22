package test;

import main.IServerConnection;

public class FakeServerConnection implements IServerConnection {

    @Override
    public int getErrorCounter() {
        return 0;
    }

    @Override
    public void SendToServer(Exception error) {

    }
}
