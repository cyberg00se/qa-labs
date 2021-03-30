package lab1.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionManagerTest {
    private ExceptionManager testManager;

    @BeforeEach
    public void setUpExceptionManagerTest() {
        testManager = new ExceptionManager();
    }

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void exceptionIsCritical(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act&assert
        assertTrue(testManager.isCritical(inputException));
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassCastException.class, NoSuchMethodException.class, IllegalAccessException.class})
    public void exceptionIsNotCritical(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act&assert
        assertFalse(testManager.isCritical(inputException));
    }

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void manageCriticalException(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();
        int expected = testManager.getCritCounter() + 1;

        //act
        testManager.manageException(inputException);

        //assert
        assertEquals(expected, testManager.getCritCounter());
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassCastException.class, NoSuchMethodException.class, IllegalAccessException.class})
    public void manageUsualException(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();
        int expected = testManager.getUsualCounter() + 1;

        //act
        testManager.manageException(inputException);

        //assert
        assertEquals(expected, testManager.getUsualCounter());
    }
}