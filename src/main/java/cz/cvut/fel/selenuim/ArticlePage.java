package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage {
    WebDriver driver;

    @FindBy(css = "h1.c-article-title")
    WebElement articleTitle;

    @FindBy(css = "time")
    WebElement articlePublished;

    @FindBy(css = "abbr[title = 'Digital Object Identifier'] ~ span.c-bibliographic-information__value")
    WebElement articleDOI;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getArticleTitle() {
        return articleTitle.getText();
    }

    public String getArticlePublishedAt() {
        return articlePublished.getAttribute("datetime");
    }

    public String getArticleDOI() {
        return articleDOI.getText();
    }
}
