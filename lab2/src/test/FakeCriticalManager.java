package test;

import main.ICriticalManager;

import java.io.IOException;

public class FakeCriticalManager implements ICriticalManager {
    @Override
    public boolean isCritical(Exception input) {
        return false;
    }

    @Override
    public void ExceptionList(String file) throws IOException, ClassNotFoundException {

    }
}
