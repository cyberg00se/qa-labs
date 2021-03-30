package lab2.src.main;

import java.io.IOException;

public interface ICriticalManager {
    boolean isCritical(Exception input);
    void ExceptionList(String file) throws IOException, ClassNotFoundException;
}
