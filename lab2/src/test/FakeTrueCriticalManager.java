package test;

import main.ICriticalManager;

import java.io.IOException;

public class FakeTrueCriticalManager implements ICriticalManager {
    private boolean WillBeCritical = true;

    @Override
    public boolean isCritical(Exception input) {
        return WillBeCritical;
    }

    @Override
    public void ExceptionList(String file) throws IOException, ClassNotFoundException {

    }
}

