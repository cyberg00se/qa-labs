package test;

import main.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionManagerTest {
    private ExceptionManager testExceptionManager;

    private ICriticalManager fakeFalseCriticalManager;
    private ICriticalManager fakeTrueCriticalManager;

    private IServerConnection fakeServerManager;

    @BeforeEach
    public void setUp() {
        //default criticalManager.isCritical() always returns false
        fakeFalseCriticalManager = new FakeFalseCriticalManager();

        //criticalManager for returning true
        fakeTrueCriticalManager = new FakeTrueCriticalManager();

        //default serverManager always has 0 error counter
        fakeServerManager = new FakeServerConnection();
        ServerManagerFactory.SetManager(fakeServerManager);

        //uses default managers
        testExceptionManager = new ExceptionManager(fakeFalseCriticalManager);
    }

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void exceptionIsCritical(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //replace default stubs
        testExceptionManager.setCriticalManager(fakeTrueCriticalManager);
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act&assert
        assertTrue(testExceptionManager.isCritical(inputException));
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassCastException.class, NoSuchMethodException.class, IllegalAccessException.class})
    public void exceptionIsNotCritical (Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act&assert
        assertFalse(testExceptionManager.isCritical(inputException));
    }

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void manageCriticalExceptionCounter(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //replace default stubs
        testExceptionManager.setCriticalManager(fakeTrueCriticalManager);
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();
        int expected = testExceptionManager.getCritCounter() + 1;

        //act
        testExceptionManager.manageException(inputException);

        //assert
        assertEquals(expected, testExceptionManager.getCritCounter());
    }

    @ParameterizedTest
    @ValueSource(classes = {ClassCastException.class, NoSuchMethodException.class, IllegalAccessException.class})
    public void manageUsualExceptionCounter(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();
        int expected = testExceptionManager.getUsualCounter() + 1;

        //act
        testExceptionManager.manageException(inputException);

        //assert
        assertEquals(expected, testExceptionManager.getUsualCounter());
    }
}