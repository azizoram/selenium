package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUp {
    WebDriver driver;

    @FindBy(id = "login-box-email")
    WebElement email;

    @FindBy(id = "login-box-pw")
    WebElement password;

    @FindBy(css = "button[title = 'Log in']")
    WebElement loginBtn;

    public SignUp(WebDriver driver) {
        if(driver.getTitle().equals("Create Account - Springer")) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public void setEmail(String emailString) {
        email.sendKeys(emailString);
    }

    public void setPassword(String passwordString) {
        password.sendKeys(passwordString);
    }

    public void clickOnLoginBtn() {
        loginBtn.click();
    }

    public MainPage logIn(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
        this.clickOnLoginBtn();
        return new MainPage(driver);
    }
}
