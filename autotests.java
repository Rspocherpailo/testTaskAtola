package atola.autotests.atola;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class autotests {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dell/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        //driver.manage().window().setSize();
        driver.get("https://www.atola.com/");
    }

    @Test
    public void existanceOfSearch() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.click();
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void sizeofSearch() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        Dimension searchFieldActual = searchField.getSize();
        driver.manage().window().maximize();
        Dimension searchFieldSize = searchField.getSize();
        Assert.assertEquals(searchFieldActual, searchFieldSize);
    }

    @Test
    public void SearchSeagTitlePositive() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.sendKeys("Seagate\n");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchWindow = driver.findElement(By.xpath("//*[@id=\"ss360-layer\"]/div[1]/div[1]/div[1]/a[1]"));
        Assert.assertEquals(searchWindow.getText(), "Connecting Seagate Drives to Serial Port");
    }

    @Test
    public void SearchSeagLinkPositive() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.sendKeys("Seagate\n");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchLink = driver.findElement(By.xpath("//*[@id=\"ss360-layer\"]/div[1]/div[1]/div[1]/a[2]"));
        Assert.assertEquals(searchLink.getText(), "http://blog.atola.com/connecting-seagate-drives-to-disksense-unit");
    }

    @Test
    public void SearchNegative() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.sendKeys("sdweh3h2hre9938d\n"); //Must be negative --????
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchLink = driver.findElement(By.xpath("//*[@id=\"ss360-layer\"]/div[1]/div[1]/div[1]/a[1]"));
        //System.out.println(searchLink.getText());
        Assert.assertEquals(searchLink.getText(), "Seghash - Open-source tool for segmented hashing");
    }

    @Test
    public void SearchNegativeNowRealNegative() {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.sendKeys("/.,./\n");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement sorryMessage = driver.findElement(By.xpath("//*[@id=\"ss360-no-results\"]"));
        //System.out.println(searchLink.getText());
        Assert.assertEquals(sorryMessage.getText(), "Sorry, we have not found any matches for your query.");
    }

    @Test
    public void SearchExitButtonCheck() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("searchBox"));
        searchField.sendKeys("Seagate\n");
        Thread.sleep(5000);
        WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"ss360CloseLayerButton\"]"));
        closeButton.click();
        Thread.sleep(3000);
        WebElement AtolaImage = driver.findElement(By.xpath("//*[@id=\"body\"]/div[1]/div[1]/div/div[1]/a/img"));
        Boolean i = AtolaImage.isDisplayed();
        Assert.assertNotEquals(i, "true");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}