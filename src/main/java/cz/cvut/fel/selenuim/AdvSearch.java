package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvSearch {
    WebDriver driver;

    @FindBy(id = "all-words")
    WebElement allWordsInput;

    @FindBy(id = "least-words")
    WebElement leastWordsInput;

    @FindBy(id = "date-facet-mode")
    WebElement dateModeSelect;

    @FindBy(id = "facet-start-year")
    WebElement startYearInput;

    @FindBy(id = "facet-end-year")
    WebElement endYearInput;

    @FindBy(id = "submit-advanced-search")
    WebElement searchSubmitBtn;

    public AdvSearch(WebDriver driver) {
        if(driver.getTitle().equals("Advanced Search - Springer")) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public void setAllWordsInput(String allWords) {
        allWordsInput.sendKeys(allWords);
    }

    public void setLeastWordsInput(String leastWords) {
        leastWordsInput.sendKeys(leastWords);
    }

    public void setDateModeSelect(String modeSelect) {
        Select mode = new Select(dateModeSelect);
        mode.selectByVisibleText(modeSelect);
    }

    public void setStartYearInput(String startYear) {
        startYearInput.sendKeys(startYear);
    }

    public void setEndYearInput(String endYear) {
        endYearInput.sendKeys(endYear);
    }

    public void clickOnSearchSubmitBtn() {
        searchSubmitBtn.click();
    }

    public SearchResult submit(String allWords, String leastWords, String modeSelect, String startYear, String endYear) {
        setAllWordsInput(allWords);
        setLeastWordsInput(leastWords);
        setDateModeSelect(modeSelect);
        setStartYearInput(startYear);
        setEndYearInput(endYear);
        clickOnSearchSubmitBtn();
        return new SearchResult(driver);
    }

}
