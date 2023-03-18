package lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class AppTest {

    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement fromPort;

    @FindBy(xpath="//option[. = 'Boston']")
    private WebElement fromPortOption;

    @FindBy(name = "toPort")
    private WebElement toPort;

    @FindBy(xpath="//option[. = 'Dublin']")
    private WebElement toPortOption;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(4) .btn")
    private WebElement chooseFlightButton;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "cardType")
    private WebElement cardTypeInput;

    @FindBy(xpath="//option[. = 'American Express']")
    private WebElement cardTypeOption;

    @FindBy(css = "option:nth-child(2)")
    private WebElement cardTypeOption2;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumberInput;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonthInput;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYearInput;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardInput;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseFlightButton;



    @Test
    void test(FirefoxDriver driver) {
        driver.get("https://blazedemo.com/");
        PageFactory.initElements(driver, this);
        fromPort.click();
        fromPortOption.click();
        toPort.click();
        toPortOption.click();
        findFlightsButton.click();
        chooseFlightButton.click();
        nameInput.sendKeys("Filipe");
        addressInput.sendKeys("Second Str");
        cityInput.sendKeys("TwonTwon");
        stateInput.sendKeys("SomePlace");
        zipCodeInput.sendKeys("12345");
        cardTypeInput.click();
        cardTypeOption.click();
        creditCardNumberInput.sendKeys("123456");
        creditCardMonthInput.sendKeys("4");
        creditCardYearInput.sendKeys("2025");
        nameOnCardInput.sendKeys("Filipe");
        purchaseFlightButton.click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}
