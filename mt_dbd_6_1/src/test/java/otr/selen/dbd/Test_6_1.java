package otr.selen.dbd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Test_6_1 {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "e:\\geckodriver\\geckodriver.exe");  // driver dlya FF mojno v cfg zagrujat
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //zaderjka pered owibkoi
        driver.manage().window().maximize(); //fullscreen
        driver.get("http://eb-emc-test-ufos.otr.ru:8889/sufdclient/index.zul");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }

    public void scroller(String nme) {
        driver.findElement(By.xpath("//span[text() = ' " + nme + "']")).click();
    }

    protected boolean isElementPresent() {
        try {
            driver.findElement(By.xpath("//button[@class=\"filter-button filter-plank-cancel-button z-button\"]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void filterLoop() {

/*            while (isElementPresent()) {
                driver.findElement(By.xpath("//button[@class=\"filter-button filter-plank-cancel-button z-button\"]")).click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        int count = driver.findElements(By.xpath("//button[@class=\"filter-button filter-plank-cancel-button z-button\"]")).size();
        for (int i = 0; i < count; i++) {
            driver.findElement(By.xpath("//button[@class=\"filter-button filter-plank-cancel-button z-button\"]")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_6_1() {
        driver.findElement(By.id("user")).sendKeys("9500_Andreev.AA");
        driver.findElement(By.id("psw")).sendKeys("Oracle33");
        driver.findElement(By.id("okButton")).click();
        scroller("Все сервисы");
        scroller("Управление расходами");
        scroller("Бюджетная роспись");
        scroller("Справка ГРБС/ГАИФ об изменении СБР");
        scroller("Спец.полномочия");
        filterLoop();

    }
}
