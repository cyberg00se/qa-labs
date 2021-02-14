import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionManagerTest {
    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void exceptionIsCritical(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        ExceptionManager manager = new ExceptionManager();
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        boolean expected = true;

        //act
        boolean actual = manager.isCritical(inputException);

        //assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassCastException.class, NoSuchMethodException.class, IllegalAccessException.class})
    public void exceptionIsNotCritical(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        ExceptionManager manager = new ExceptionManager();
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        boolean expected = false;

        //act
        boolean actual = manager.isCritical(inputException);

        //assert
        assertEquals(expected, actual);
    }
}