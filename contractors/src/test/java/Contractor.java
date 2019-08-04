import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Contractor {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Contractors() throws Exception {
        driver.get("http://contractorsinsurancereview.com/ExampleForm/");
        Fill_Name("name", "Name1");
        Fill_Email("email", "vsasch@i.ua");
        Fill_Phone("phone", "0546089743");
        Fill_Company("company", "Inetex");
        Select_Employees();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of Employees'])[1]/following::button[1]")).click();

        try {
            assertEquals("You'll hear from us soon.", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Thank You!'])[1]/following::h2[1]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    private void Select_Employees() {
        driver.findElement(By.id("employees")).click();
        new Select(driver.findElement(By.id("employees"))).selectByVisibleText("51-500");
        driver.findElement(By.id("employees")).click();
    }

    private void Fill_Company(String company, String inetex) {
        ClicAndClear(company);
        driver.findElement(By.id(company)).sendKeys(inetex);
    }

    private void Fill_Phone(String phone, String s) {
        ClicAndClear(phone);
        driver.findElement(By.id(phone)).sendKeys(s);
    }

    private void Fill_Email(String email, String s) {
        ClicAndClear(email);
        driver.findElement(By.id(email)).sendKeys(s);
    }

    private void Fill_Name(String name, String name1) {
        ClicAndClear(name);
        driver.findElement(By.id(name)).sendKeys(name1);
    }

    private void ClicAndClear(String name) {
        driver.findElement(By.id(name)).click();
        driver.findElement(By.id(name)).clear();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
