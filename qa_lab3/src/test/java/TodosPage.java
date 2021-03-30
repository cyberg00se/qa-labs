import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TodosPage {
    public WebDriver driver;
    public TodosPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/ng-view/section/header/form/input")
    private WebElement newTodoField;
    private int todosCount;

    private List<WebElement> todoNamesList;
    private List<WebElement> todoChecksList;

    public int getTodosCount() {
        return todosCount;
    }
    
    @FindBy(xpath = "/html/body/ng-view/section/footer/button")
    private WebElement clearCompleted;
    
    @FindBy(xpath = "/html/body/ng-view/section/section/ul/li/div/input")
    private WebElement isCheckedTodoField;

    @FindBy(xpath = "/html/body/ng-view/section/footer/span/strong")
    private WebElement foundItemsCount;

    public String getFoundItemsCount() {
        return foundItemsCount.getText();
    }

    public void inputNewTodo(String todo) {
        newTodoField.sendKeys(todo + Keys.ENTER);
        todosCount++;
    }
    
    public void checkFirstTodo(){ 
        isCheckedTodoField.click();
    }

   /* public void checkTodo(int index) {
        todoChecksList = driver.findElements(By.cssSelector("ul.todo-list > li"));
        todoChecksList.get(index).click();
    }*/

    public String getTodoName(int index) {
        todoNamesList = driver.findElements(By.cssSelector("label.ng-binding"));
        return todoNamesList.get(index).getText();
    }

    /*public boolean getTodoIsChecked(int index){
        todoChecksList = driver.findElements(By.cssSelector("ul.todo-list > li"));
        return todoNamesList.get(index).getAttribute("class").contains("completed");
    }*/
}
