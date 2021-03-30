import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {
    public static TodosPage todosPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
        todosPage = new TodosPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("todospage"));
    }

    @Test
    public void makeNewTodo() {
        todosPage.inputNewTodo("my task 1");
        assertEquals("my task 1", todosPage.getTodoName(todosPage.getTodosCount()-1));
    }
    
    @Test
    public void clearCompletedTest(){
        todosPage.inputNewTodo("my task 1" + Keys.ENTER);
        todosPage.inputNewTodo("my task 2" + Keys.ENTER);

        todosPage.checkFirstTodo();

        todosPage.clickClearCompleted();
        assertEquals(todosPage.getFoundItemsCount(),"1");
    }

    /*@Test
    public void checkNewTodoTest(){
        todosPage.inputNewTodo("my task 2");
        todosPage.checkTodo(todosPage.getTodosCount()-1);
        assertEquals(true, todosPage.getTodoIsChecked(todosPage.getTodosCount()-1));
    }*/

    @AfterAll
    public static void tearDown() {
        //driver.quit();
    }
}
