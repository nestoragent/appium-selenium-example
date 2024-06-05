package com.xm.pages.web;

import com.xm.lib.Init;
import com.xm.lib.Props;
import com.xm.lib.pageFactory.PageEntry;
import com.xm.model.StockCFD;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@PageEntry(title = "Stock Certain")
public class StockCertainPage extends WebAnyPage {

    @FindBy(css = "h1.ltr")
    public WebElement pageHeader;

    @FindBy(css = "td[data-xm-qa-name='symbols__value']")
    public WebElement textSymbols;

    @FindBy(css = "table.table tr.hidden-xs td[data-xm-qa-name='spreads_as_low_as__value']")
    public WebElement textSpreadAsLowAs;

    @FindBy(css = "td[data-xm-qa-name='min_max_trade_size__value']")
    public WebElement textMinMaxTradeSize;

    @FindBy(css = "td[data-xm-qa-name='margin_requirement__value']")
    public WebElement textMinMarginPercentage;

    @FindBy(css = "td[data-xm-qa-name='swap_value_in_margin_currency_long__value']")
    public WebElement textSwapValueMarginCurrencyLong;

    @FindBy(css = "td[data-xm-qa-name='swap_value_in_margin_currency_short__value']")
    public WebElement textSwapValueMarginCurrencyShort;

    @FindBy(css = "td[data-xm-qa-name='limit_and_stop_levels__title']:last-child")
    public WebElement textLimitAndStopLevels;

    public StockCertainPage() {
        PageFactory.initElements(Init.getWebDriver(), this);
        new WebDriverWait(Init.getWebDriver(), Duration.ofSeconds(Props.getLong("implicit.wait.duration")))
                .until(ExpectedConditions.visibilityOf(pageHeader));
    }

    public void check_trading_conditions_for(String stock) {
        StockCFD stockCFD = (StockCFD) Init.getStash().get(stock);
        Assertions.assertEquals(stockCFD.getSymbol(), textSymbols.getText().strip());
        Assertions.assertEquals(stockCFD.getMinSpread(), textSpreadAsLowAs.getText().strip());
        Assertions.assertEquals(stockCFD.getMinMaxTradeSize(), textMinMaxTradeSize.getText().strip());
        Assertions.assertEquals(stockCFD.getMarginRequirement(), textMinMarginPercentage.getText().strip());
        Assertions.assertEquals(stockCFD.getSwapLong(), textSwapValueMarginCurrencyLong.getText().strip());
        Assertions.assertEquals(stockCFD.getSwapShort(), textSwapValueMarginCurrencyShort.getText().strip());
        Assertions.assertEquals(stockCFD.getLimitStopLevel(), textLimitAndStopLevels.getText().strip());
    }
}
