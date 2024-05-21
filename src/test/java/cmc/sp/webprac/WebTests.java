package cmc.sp.webprac;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTests {
    private final String url = "http://localhost:8080";

    @Test
    void indexPageTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/index");
        assertEquals("Главная страница", driver.getTitle());
        driver.quit();
    }

    @Test
    void getClientListTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/index");

        WebElement peopleButton = driver.findElement(By.id("clients-button"));
        peopleButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Управление клиентами", driver.getTitle());

        List<WebElement> rows = driver.findElement(By.id("clients-table")).findElements(By.tagName("tr"));
        List<WebElement> cells = rows.get(3).findElements(By.tagName("td"));

        assertEquals(cells.get(2).getText(), "4035759570");

        driver.quit();
    }

    @Test
    void createClientTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/clients");

        driver.findElement(By.id("add-client-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("radio_individual")).click();

        List<WebElement> inputs = driver.findElement(By.id("form1")).findElements(By.tagName("input"));
        inputs.get(1).sendKeys("Иванов");
        inputs.get(2).sendKeys("Иван");
        inputs.get(3).sendKeys("Иванович");
        inputs.get(4).sendKeys("г. Москва");
        inputs.get(5).sendKeys("1234567890");
        inputs.get(6).sendKeys("+79998887766");
        var btn = driver.findElement(By.id("save-button"));
        new Actions(driver)
                .scrollByAmount(0, 200)
                .perform();
        btn.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Клиент", driver.getTitle());
        assertEquals("1234567890", driver.findElement(By.id("passport")).getAttribute("value"));

        driver.quit();
    }

    @Test
    void editClientTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/client?client_id=3&client_type=1");

        var input = driver.findElement(By.id("contactName"));
        assertEquals("Артём", input.getAttribute("value"));
        assertEquals("true", input.getAttribute("disabled"));

        new Actions(driver)
                .scrollByAmount(0, 500)
                .perform();
        driver.findElement(By.id("edit-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals(null, input.getAttribute("disabled"));
        input.clear();
        input.sendKeys("Иван");
        driver.findElement(By.id("save-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Иван", driver.findElement(By.id("contactName")).getAttribute("value"));
        assertEquals("true", driver.findElement(By.id("contactName")).getAttribute("disabled"));

        driver.quit();
    }

    @Test
    void getAccountTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/clients");

        driver.findElement(By.id("account-id")).sendKeys("5");
        driver.findElement(By.id("button-account-go")).click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Счёт", driver.getTitle());
        assertEquals("5", driver.findElement(By.id("id")).getAttribute("value"));
        driver.quit();
    }

    @Test
    void createAccountTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/client?client_id=5&client_type=0");

        new Actions(driver)
                .scrollByAmount(0, 500)
                .perform();
        driver.findElement(By.id("add-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Счёт", driver.getTitle());
        assertEquals("Ковалева Алёна Платоновна", driver.findElement(By.id("holder_name_individual")).getAttribute("value"));
        driver.quit();
    }

    @Test
    void getServiceListTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/index");

        WebElement peopleButton = driver.findElement(By.id("services-button"));
        peopleButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Управление услугами", driver.getTitle());

        List<WebElement> rows = driver.findElement(By.id("services-table")).findElements(By.tagName("tr"));
        List<WebElement> cells = rows.get(2).findElements(By.tagName("td"));
        assertEquals(cells.get(3).getText(), "Дополнительный пакет интернета");

        driver.quit();
    }

    @Test
    void createsServiceTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/services");

        driver.findElement(By.id("add_service")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        List<WebElement> inputs = driver.findElement(By.id("form")).findElements(By.tagName("input"));
        inputs.get(1).sendKeys("Услуга");
        inputs.get(2).sendKeys("0");
        inputs.get(3).sendKeys("500");
        inputs.get(4).sendKeys("0");
        inputs.get(7).sendKeys("40");
        inputs.get(9).sendKeys("Описание");

        new Actions(driver)
                .scrollByAmount(0, 1000)
                .perform();
        driver.findElement(By.id("save-button")).click();


        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Услуга", driver.getTitle());
        assertEquals("40", driver.findElement(By.id("package-internet")).getAttribute("value"));

        driver.quit();
    }

    @Test
    void deleteServiceTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/service?service_id=4");

        new Actions(driver)
                .scrollByAmount(0, 1000)
                .perform();
        driver.findElement(By.id("remove-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Управление услугами", driver.getTitle());


        driver.get(url + "/service?service_id=4");
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Ошибка!", driver.getTitle());

        driver.quit();
    }

    @Test
    void getOperationsTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/account?account_id=6");

        new Actions(driver)
                .scrollByAmount(0, 1000)
                .perform();
        driver.findElement(By.id("operations-button")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Операции по счёту", driver.getTitle());

        List<WebElement> rows = driver.findElement(By.id("operation-table")).findElements(By.tagName("tr"));
        List<WebElement> cells = rows.get(1).findElements(By.tagName("td"));

        assertEquals("Подключение услуги", cells.get(1).getText());

        driver.quit();
    }

    @Test
    void disconnectServiceTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get(url + "/account?account_id=6");

        assertEquals("850", driver.findElement(By.id("balance")).getAttribute("value"));

        new Actions(driver)
                .scrollByAmount(0, 500)
                .perform();
        var table = driver.findElement(By.id("show-table"));
        var rows = table.findElements(By.tagName("tr"));
        var cells = rows.get(1).findElements(By.tagName("td"));
        var button = cells.get(4).findElement(By.tagName("a"));
        button.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        assertEquals("Счёт", driver.getTitle());
        assertEquals("800", driver.findElement(By.id("balance")).getAttribute("value"));
        assertEquals("К счёту ещё не подключено ни одной услуги", driver.findElement(By.tagName("p")).getText());

        driver.quit();
    }
}
