package lab2.src.test;

import lab2.src.main.ICriticalManager;

import java.io.IOException;

public class FakeFalseCriticalManager implements ICriticalManager {
    private boolean WillBeCritical = false;

    @Override
    public boolean isCritical(Exception input) {
        return WillBeCritical;
    }

    @Override
    public void ExceptionList(String file) throws IOException, ClassNotFoundException {

    }
}

