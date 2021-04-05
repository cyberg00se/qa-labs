import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(css = "ul.todo-list > li")
    private List<WebElement> todoList;

    @FindBy(css = "label.ng-binding")
    private List<WebElement> todoNamesList;
    
    @FindBy(xpath = "/html/body/ng-view/section/footer/button")
    private WebElement clearCompleted;
    
    @FindBy(xpath = "/html/body/ng-view/section/section/ul/li/div/input")
    private WebElement isCheckedTodoField;

    @FindBy(xpath = "/html/body/ng-view/section/footer/span/strong")
    private WebElement foundItemsCount;

    public int getAllItemsCount() {
        return todoList.size();
    }

    public int getActiveItemsCount() {
        return Integer.parseInt(foundItemsCount.getText());
    }

    public void inputNewTodo(String todo) {
        newTodoField.sendKeys(todo + Keys.ENTER);
    }
    
    public void toggleFirstTodo(){
        isCheckedTodoField.click();
    }
    public void clickClearCompleted(){
        clearCompleted.click();
    }

    public void toggleTodo(int index) {
        WebElement checkbox = todoList.get(index).findElement(By.cssSelector("div.view > input"));
        checkbox.click();
    }

    public String getTodoName(int index) {
        todoNamesList = driver.findElements(By.cssSelector("label.ng-binding"));
        return todoNamesList.get(index).getText();
    }

    public boolean getTodoIsChecked(int index) {
        return todoList.get(index).getAttribute("class").contains("completed");
    }
    
    public void deleteTodo(String task){
       var element = driver.findElement(By.xpath("//label[text()='"+task+"']/.."));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        var destroyB=driver.findElement(By.xpath("//label[text()='"+task+"']/..//button[@class='destroy']"));
        destroyB.click();
    }
    
    public String editTodo(String todo, String editedTodo){
        var element=driver.findElement(By.xpath("//label[text()='"+todo+"']/..//label"));
        Actions act=new Actions(driver);
        act.doubleClick(element).build().perform();
        var input=driver.findElement(By.xpath("//label[text()='"+todo+"']/../../form/input"));
        input.sendKeys(Keys.CONTROL+"a");
        input.sendKeys(editedTodo);
        input.submit();

        return element.getText();
    }
}
