package org.com.app.Service.Impl;

import org.com.app.Model.Response.Artikel;
import org.com.app.Model.Response.ResponseData;
import org.com.app.Service.SeleniumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SeleniumServiceImpl implements SeleniumService {

    @Override
    public ResponseData getListArtikel(String artikel) {
        ResponseData responseData = new ResponseData();
        HashMap<String, Object> data = new HashMap<>();

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yayat\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.get(artikel);


            List<WebElement> newsHeadlines = driver.findElements(By.cssSelector("div.media__text h2.media__title a.media__link"));

            StringBuilder scrapedData = new StringBuilder();
            scrapedData.append("Berita terbaru dari Detik:\n\n");

            List<Artikel> artikels= new ArrayList<>();


            for (WebElement headline : newsHeadlines) {
                Artikel artikelModel = new Artikel();
                artikelModel.setJudul(headline.getText());
                artikelModel.setLink(headline.getAttribute("href"));
                artikels.add(artikelModel);
            }
            data.put("Berita Hari Ini", artikels);
            responseData.setResult(HttpStatus.valueOf("OK"));
            responseData.setMessage("Scrapping Berhasil");
            responseData.setData(data);

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResult(HttpStatus.valueOf("INTERNAL_SERVER_ERROR"));
            responseData.setMessage("Terjadi kesalahan saat scraping: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        return responseData;
    }
}
