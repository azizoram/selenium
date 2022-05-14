package cz.cvut.fel.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static WebDriver driver;
    private static MainPage homePage;

    private static class Article {
        String title;
        String publishDate;
        String doi;

        private Article(String title, String publishDate, String doi) {
            this.title = title;
            this.publishDate = publishDate;
            this.doi = doi;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %s", title, publishDate, doi);
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "/C:/Users/ramir/OneDrive/Рабочий стол/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://link.springer.com/");

        List<Article> articles = new ArrayList<>();
        String email = "email@example.com";
        String password = "yourpassword";

        homePage = new MainPage(driver);
        homePage.clickOnAcceptCookie();

        homePage.openSignup().logIn(email, password);
        AdvSearch advSearch = homePage.openAdvancedSearchLink();
        SearchResult search = advSearch.submit(
                "Page Object Model",
                "Sellenium Testing",
                "between",
                "2022",
                "2022").clickOnArticleFilter();

        for (int i = 0; i < 4; i++) {
            ArticlePage articlePage = search.goToArticle(i);
            String title = articlePage.getArticleTitle();
            String date = articlePage.getArticlePublishedAt();
            String doi = articlePage.getArticleDOI();

            Article article = new Article(title, date, doi);
            articles.add(article);

            driver.navigate().back();
        }

        try (PrintWriter printWriter = new PrintWriter("src/test/resources/cz/cvut/fel/ts1/articles.csv")) {
            for (Article article : articles) {
                printWriter.println(article.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        driver.close();
    }
}

