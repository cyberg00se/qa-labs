package lab2.src.test;

import lab2.src.main.IServerConnection;

public class FakeServerConnection implements IServerConnection {

    @Override
    public int getErrorCounter() {
        return 0;
    }

    @Override
    public void SendToServer(Exception error) {

    }
}
