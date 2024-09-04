import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomateGuestRegForm {
    WebDriver driver;

    @BeforeAll
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headed--");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
}
    @DisplayName("Check if webform input and Submission Workflow is Successful")
    @Test
    public void automateWebform() throws InterruptedException {

        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");


        List<WebElement> elements = driver.findElements(By.cssSelector(".input-text.ur-frontend-field"));
        elements.get(0).sendKeys("Shuhana");
        elements.get(1).sendKeys("suhanariya56679@gmail.com");
        elements.get(2).sendKeys("Subana#&1236");
        elements.get(3).sendKeys("Riya");
        elements.get(5).sendKeys("Bangladeshi");



        //country
        Select select = new Select(driver.findElement(By.id("country_1665629257")));
        select.selectByVisibleText("Bangladesh");


        //gender
        List<WebElement> radiobtn=driver.findElements(By.cssSelector("[type=radio]"));
        Actions actions = new Actions(driver);
        Utils.scrollDown(driver,300);
        actions.click(radiobtn.get(1)).perform();


            //Dob

        // Date of Birth
        WebElement datePicker = driver.findElement(By.className("ur-flatpickr-field"));
        datePicker.click();
        Thread.sleep(1000); // Wait for date picker to be fully loaded

        //select Month

            String requiredMonth="August";

            // Locate all month options
            List<WebElement> monthOptions = driver.findElements(By.cssSelector("select.flatpickr-monthDropdown-months option"));

// Iterate through options and select the desired month
            for (WebElement option : monthOptions) {
                if (option.getText().equals(requiredMonth)) {
                    option.click();
                    break;
                }
            }



        // Select Year
        WebElement yearElement = driver.findElement(By.className("cur-year"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = '2024';", yearElement);
        Thread.sleep(2000); // Wait for the year to be set

        //Select date

        Faker faker = new Faker();
        int date         = faker.number().numberBetween(1,30);
        String spanText  = String.valueOf(date);

        WebElement spanElement = driver.findElement(By.xpath("//span[text()='" + spanText + "']"));

        spanElement.click();




        Thread.sleep(1000);

        Utils.scrollDown(driver,400);

        //Terms and condition
        List <WebElement> checkBox =driver.findElements(By.cssSelector("[type=checkbox]"));
        checkBox.get(0).click();
        Thread.sleep(2000);
        Utils.scrollDown(driver,200);

        WebElement submitSpan = driver.findElement(By.className("btn"));
        submitSpan.click();
        Thread.sleep(2000);



//        // Locate the buttons using XPath
//        List<WebElement> submitButtons = driver.findElements(By.xpath("//button[@type='submit']"));
//
//        WebElement firstSubmitButton = submitButtons.get(0);
//        Thread.sleep(200);
//        firstSubmitButton.click();


//
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement ul = driver.findElement(By.xpath("//ul[text() = \"User successfully registered.\"]"));
        wait.until(
                ExpectedConditions.visibilityOf(ul));
        String successful_msg = ul.getText();
        Assertions.assertTrue(successful_msg.contains("User successfully registered."));

    }

    //@AfterAll
    public void quitBrowser(){
        driver.quit();
    }

}



