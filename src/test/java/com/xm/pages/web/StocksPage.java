package com.xm.pages.web;

import com.xm.lib.Init;
import com.xm.lib.Props;
import com.xm.lib.pageFactory.PageEntry;
import com.xm.model.StockCFD;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@PageEntry(title = "Stocks")
public class StocksPage extends WebAnyPage {

    private final static String CSS_BUTTON_FILTER = "button[data-value='%s']";
    private final static String XPATH_STOCK_SEARCH_ROW = "//table[contains(@id, 'DataTables')]/tbody/tr[@role='row']/td[@data-xm-qa-name='symbolWithDescription' and contains(text(), '%s')]/..";
    private final static String CSS_BUTTON_NEXT = "div[id*='DataTables'] a.next:not(.disabled)";

    @FindBy(css = "li.active > a[href*='/stocks']")
    public WebElement buttonStocksActive;

    public StocksPage() {
        PageFactory.initElements(Init.getWebDriver(), this);
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOf(buttonStocksActive));
    }

    public void choose_stock_filter(String filter) {
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));

        scrollToByActions(Init.getWebDriver().findElement(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));

        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));
        clickByJs(Init.getWebDriver().findElement(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter))));
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.attributeContains(By.cssSelector(CSS_BUTTON_FILTER.formatted(filter)), "class", "active"));
    }

    public void find_stock_in_table(String stock) throws Exception {
        List<WebElement> stocks = Init.getWebDriver().findElements(By.xpath(XPATH_STOCK_SEARCH_ROW.formatted(stock)));
        List<WebElement> buttonsTableNext = Init.getWebDriver().findElements(By.cssSelector(CSS_BUTTON_NEXT));

        boolean find = false;
        while (stocks.isEmpty() && !buttonsTableNext.isEmpty()) {
            clickByJs(buttonsTableNext.getFirst());
            stocks = Init.getWebDriver().findElements(By.xpath(XPATH_STOCK_SEARCH_ROW.formatted(stock)));
            if (!stocks.isEmpty()) {
                find = true;
            } else {
                buttonsTableNext = Init.getWebDriver().findElements(By.cssSelector(CSS_BUTTON_NEXT));
            }
        }

        if (!find) {
            throw new Exception("Failed to find stock: " + stock);
        }
    }

    public void remember_stock_in_table(String stock) {
        WebElement stockRow = Init.getWebDriver().findElement(By.xpath(XPATH_STOCK_SEARCH_ROW.formatted(stock)));
        StockCFD stockCFD = new StockCFD();
        stockCFD.setSymbolWithDescription(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='symbolWithDescription']")).getText().strip());
        stockCFD.setSymbol(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='symbol']")).getText().strip());
        stockCFD.setMinSpread(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='minSpread']")).getText().strip());
        stockCFD.setMinMaxTradeSize(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='minMaxTradeSize']")).getText().strip());
        stockCFD.setMarginRequirement(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='marginRequirement']")).getText().strip());
        stockCFD.setSwapLong(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='swapLong']")).getText().strip());
        stockCFD.setSwapShort(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='swapShort']")).getText().strip());
        stockCFD.setLimitStopLevel(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='limitStopLevel']")).getText().strip());
        stockCFD.setUrl(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='url']/a")).getAttribute("href"));

        Init.getStash().put(stock, stockCFD);
    }

    public void click_read_more_for(String stock) {
        WebElement stockRow = Init.getWebDriver().findElement(By.xpath(XPATH_STOCK_SEARCH_ROW.formatted(stock)));
        clickByJs(stockRow.findElement(By.xpath("./td[@data-xm-qa-name='url']/a")));
    }
}
