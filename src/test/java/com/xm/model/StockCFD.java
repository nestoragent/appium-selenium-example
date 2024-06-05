package com.xm.model;

import lombok.Data;

@Data
public class StockCFD {
    private String symbolWithDescription;
    private String symbol;
    private String minSpread;
    private String minMaxTradeSize;
    private String marginRequirement;
    private String swapLong;
    private String swapShort;
    private String limitStopLevel;
    private String url;
}
