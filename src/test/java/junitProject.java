import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class junitProject {
    WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Check if the form submitted properly")
    @Test
    public void automateForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Tomal");
        driver.findElement(By.id("edit-number")).sendKeys("01771344670");

        List<WebElement> buttonElement=driver.findElements(By.className("ui-checkboxradio-label"));
        buttonElement.get(0).click();

        WebElement date= driver.findElement(By.id("edit-date"));
        date.click();
        date.sendKeys("01012023");

        scroll(0,500);

        driver.findElement(By.id("edit-email")).sendKeys("tomal@gmail.com");

        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("This is Tomal, Fazle Rabby Pathan Tomal");

        scroll(0,800);

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/jpg2png.zip");


        Thread.sleep(2000);

        driver.findElement(By.id("edit-age")).click();

        Thread.sleep(1000);

        driver.findElement(By.id("edit-submit")).click();

        String message = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(message.contains("Thank you for your submission!"));

    }


    public void scroll(int x,int y){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scroll("+x+","+y+")");
        //js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }
}
