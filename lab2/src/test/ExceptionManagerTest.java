package test;

import main.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ExceptionManagerTest {
    private ExceptionManager testExceptionManager;
    private ICriticalManager fakeCriticalManager;
    private IServerConnection fakeServerManager;

    @BeforeAll
    public void setUp() {
        fakeCriticalManager = new FakeCriticalManager();
        fakeServerManager = new FakeServerConnection();

        ServerManagerFactory.SetManager(fakeServerManager);

        testExceptionManager = new ExceptionManager(fakeCriticalManager);
    }

    @Test
    public void exceptionIsCritical() {
        //arrange

        //act

        //assert
    }

    @Test
    public void exceptionIsNotCritical () {

    }

    @Test
    public void manageCriticalException() {

    }

    @Test
    public void manageUsualException( ) {

    }
}