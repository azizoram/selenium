import cz.cvut.fel.selenuim.ArticlePage;
import cz.cvut.fel.selenuim.MainPage;
import cz.cvut.fel.selenuim.SearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class SeleniumTest {
    WebDriver driver;
    MainPage homePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://link.springer.com/");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "articles.csv")
    public void springerTest(String title, String publishDate, String doi) {
        String email = "email@example.com";
        String password = "password";

        homePage = new MainPage(driver);
        homePage.clickOnAcceptCookie();

        homePage.openSignup().logIn(email, password);
        homePage.searchThisTitle(title);
        SearchResult searchResult = homePage.clickOnSearch();
        ArticlePage article = searchResult.goToArticle(0);

        Assertions.assertEquals(publishDate, article.getArticlePublishedAt());
        Assertions.assertEquals(doi, article.getArticleDOI());

        driver.close();
    }
}
