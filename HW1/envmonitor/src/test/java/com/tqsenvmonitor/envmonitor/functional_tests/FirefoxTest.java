package com.tqsenvmonitor.envmonitor.functional_tests;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

@ExtendWith(SeleniumJupiter.class)
public class FirefoxTest {
    private WebDriver driver;
    
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUp() {
      driver = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown() {
      driver.quit();
    }

    @Test
    void test(){
        driver.get("http://localhost:8083");
        driver.manage().window().setSize(new Dimension(1848, 1053));

        // Esperar até que o campo de entrada esteja visível
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));

        cityInput.click();
        cityInput.sendKeys("Faro");
        driver.findElement(By.cssSelector("button:nth-child(2)")).click();

        // Esperar até que o campo de entrada esteja visível e limpar seu conteúdo
        WebElement cityInput2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        cityInput2.clear();
        cityInput2.sendKeys("Porto");

        driver.findElement(By.cssSelector("form > button:nth-child(3)")).click();
        driver.findElement(By.cssSelector("form")).click();

        // Esperar até que o campo de entrada esteja visível e limpar seu conteúdo novamente
        WebElement cityInput3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        cityInput3.clear();
        cityInput3.sendKeys("cabo");

        driver.findElement(By.cssSelector("button:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".statistics > button")).click();
    }

}
