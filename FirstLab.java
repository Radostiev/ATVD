package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class FirstLab {

    private WebDriver chromeDriver;

    //private static final String baseUrl = "https://www.nmu.org.ua/ua/";
    private static final String baseUrl = "https://www.yakaboo.ua/";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void preconditions(){
        chromeDriver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){chromeDriver.quit();}

//    @Test
//    public void testHeaderExists() {
//        WebElement header = chromeDriver.findElement(By.xpath("/html/body/div[1]/header"));
//        Assert.assertNotNull(header);
//    }

//    @Test
//    public void testClickOnForStudent() {
//        WebElement forStudentButton = chromeDriver.findElement(By.xpath("/html/body/div[1]/footer/div[1]/div/div/div[1]/div/div/nav/div/ul/li[2]"));
//        Assert.assertNotNull(forStudentButton);
//        forStudentButton.click();
//        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), baseUrl);
//    }
//    @Test
//    public void testSearchFieldOnForStudentPage(){
//        String studentPageUrl = "ua/content/students/";
//        chromeDriver.get(baseUrl + studentPageUrl);
//        WebElement searchField = chromeDriver.findElement(By.tagName("input"));
//        Assert.assertNotNull(searchField);
//        System.out.println( String.format("Name attribute: %s", searchField.getAttribute("name")) +
//                String.format("\nID attribute %s", searchField.getAttribute("id")) +
//                String.format("\nType attribute %s", searchField.getAttribute("type")) +
//                String.format("\nValue attribute %s", searchField.getAttribute("value")) +
//                String.format("\nPosition: (%d;%d)", searchField.getLocation().x, searchField.getLocation().y) +
//                String.format("\nSize: (%d;%d)", searchField.getSize().height, searchField.getSize().width)
//
//        );
//        String inputValue = "I need info";
//        searchField.sendKeys(inputValue);
//        Assert.assertEquals(searchField.getText(), inputValue);
//        searchField.sendKeys(Keys.ENTER);
//        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), studentPageUrl);
//    }
//    @Test
//    public void testSlider() {
//        WebElement nextButton = chromeDriver.findElement(By.className("swiper-button-next"));
//        WebElement nextButtonByCss = chromeDriver.findElement(By.cssSelector("#post-108 > div > div.wp-block-uagb-slider.uagb-block-02785fef.uagb-slider-container.aos-init.aos-animate > div.swiper-button-next"));
//        Assert.assertEquals(nextButton, nextButtonByCss);
//
//        WebElement previousButton = chromeDriver.findElement(By.className("swiper-button-prev"));
//
//        for(int i = 0; i < 20; i++) {
//            if(nextButton.getAttribute("class").contains("disabled")) {
//                previousButton.click();
//                Assert.assertTrue(previousButton.getAttribute("class").contains("disabled"));
//                Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
//            }
//            else {
//                nextButton.click();
//                Assert.assertTrue(nextButton.getAttribute("class").contains("disabled"));
//                Assert.assertFalse(previousButton.getAttribute("class").contains("disabled"));
//            }
//        }
//    }

    @Test
    public void testYakaboo() throws InterruptedException {
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"search-input\"]"));
        String inputValue = "Під куполом";
        searchField.sendKeys(inputValue);
        Assert.assertEquals(searchField.getAttribute("value"), inputValue);
        Thread.sleep(1000);
        searchField.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[8]/div[1]/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]")));
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), baseUrl);
    }
}







