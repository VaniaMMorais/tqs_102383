package lab4;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith(SeleniumJupiter.class)
public class FirefoxTest {

    @Test
    void test(FirefoxDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1848,1053));
        driver.findElement(By.name("fromPort")).click();
        {
          WebElement dropdown = driver.findElement(By.name("fromPort"));
          dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        driver.findElement(By.name("toPort")).click();
        {
          WebElement dropdown = driver.findElement(By.name("toPort"));
          dropdown.findElement(By.xpath("//option[. = 'Dublin']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(6)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Filipe");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Second Str");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("TwonTwon");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("SomePlace");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("cardType")).click();
        {
          WebElement dropdown = driver.findElement(By.id("cardType"));
          dropdown.findElement(By.xpath("//option[. = 'American Express']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(2)")).click();
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456");
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys("4");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2025");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}
