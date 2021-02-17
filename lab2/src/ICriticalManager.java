package lab2.src;

import java.io.IOException;
import java.util.List;

public interface ICriticalManager {
    boolean isCritical(Exception input);
    public List<Class<? extends Exception>> ExceptionList(String file) throws IOException, ClassNotFoundException;
}
