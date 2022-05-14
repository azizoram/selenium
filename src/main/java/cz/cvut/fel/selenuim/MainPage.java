package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;

    @FindBy(className = "register-link")
    WebElement signupLink;

    @FindBy(className = "cc-button")
    WebElement acceptCookie;

    @FindBy(css = "button.open-search-options")
    WebElement searchOptionBtn;

    @FindBy(id = "advanced-search-link")
    WebElement advancedSearchLink;

    @FindBy(id = "query")
    WebElement standardSearch;

    @FindBy(id = "search")
    WebElement searchBtn;

    public MainPage(WebDriver driver) {
        if(driver.getTitle().equals("Home - Springer")) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public SignUp openSignup() {
        signupLink.click();
        return new SignUp(driver);
    }

    public void clickOnSearchOptionBtn() {
        searchOptionBtn.click();
    }

    public AdvSearch openAdvancedSearchLink() {
        clickOnSearchOptionBtn();
        advancedSearchLink.click();
        return new AdvSearch(driver);
    }

    public void clickOnAcceptCookie() {
        acceptCookie.click();
    }

    public void searchThisTitle(String title) {
        standardSearch.sendKeys(title);
    }

    public SearchResult clickOnSearch() {
        searchBtn.click();
        return new SearchResult(driver);
    }
}
