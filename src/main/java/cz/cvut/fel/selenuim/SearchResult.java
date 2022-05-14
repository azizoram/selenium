package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResult {
    WebDriver driver;

    @FindBy(partialLinkText = "Article")
    WebElement articleFilter;

    @FindBy(css = "#results-list > li > h2 > a")
    List<WebElement> articles;

    public SearchResult(WebDriver driver) {
        if(driver.getTitle().equals("Search Results - Springer")) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }

    public SearchResult clickOnArticleFilter() {
        articleFilter.click();
        return this;
    }

    public ArticlePage goToArticle(int index) {
        articles.get(index).click();
        return new ArticlePage(driver);
    }
}
