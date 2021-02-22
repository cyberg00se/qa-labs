package lab2.src.test;

import lab2.src.main.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExceptionManagerTest {
    private ExceptionManager testExceptionManager;

    private ICriticalManager fakeFalseCriticalManager;
    private ICriticalManager fakeTrueCriticalManager;

    private IServerConnection fakeServerManager;
    private IServerConnection mockServer;

    @BeforeEach
    public void setUp() {
        //default criticalManager.isCritical() always returns false
        fakeFalseCriticalManager = new FakeFalseCriticalManager();

        //criticalManager for returning true
        fakeTrueCriticalManager = new FakeTrueCriticalManager();

        //default serverManager always has 0 error counter
        fakeServerManager = new FakeServerConnection();
        ServerManagerFactory.SetManager(fakeServerManager);

        //mock ServerManager for checking errorCounter
        mockServer = mock(ServerConnectionManager.class);

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

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void sendingExceptionToServer(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //replace default stubs
        testExceptionManager.setCriticalManager(fakeTrueCriticalManager);
        ServerManagerFactory.SetManager(mockServer);
        testExceptionManager.setServerManager(ServerManagerFactory.Create());
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act
        testExceptionManager.manageException(inputException);

        //assert? verify?
        verify(mockServer, times(1)).SendToServer(inputException);
    }

    @ParameterizedTest
    @ValueSource(classes = {NullPointerException.class, ArrayIndexOutOfBoundsException.class, FileNotFoundException.class})
    public void NOTsendingExceptionToServer(Class exceptionClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //replace default stubs
        ServerManagerFactory.SetManager(mockServer);
        testExceptionManager.setServerManager(ServerManagerFactory.Create());
        //arrange
        Exception inputException = (Exception)exceptionClass.getDeclaredConstructor().newInstance();

        //act
        testExceptionManager.manageException(inputException);

        //assert? verify?
        verify(mockServer, times(0)).SendToServer(inputException);
    }
}