package com.xm.stepDefinitions;

import com.xm.lib.Init;
import com.xm.lib.Props;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.Map;

public class CommonStepDefinitions {

    @When("^(I|User) is on page \"(.*?)\"$")
    public void init_current_page(String action, String title) throws Throwable {
        Init.getPageFactory().getPage(title);
    }

    @When("^(I|User) \\((.*?)\\) \"([^\"]*)\"$")
    public void standard_action_1(String who, String action, Object param) throws Throwable {
        Init.getPageFactory().getCurrentPage().takeAction(action, param);
    }

    @When("^(I|User) \\((.*?)\\).* \"([^\"]*)\".* \"([^\"]*)\"[^\"]*$")
    public void standard_action_2(String who, String action, String param1, String param2) throws Throwable {
        Init.getPageFactory().getCurrentPage().takeAction(action, param1, param2);
    }

    @When("^(I|User) .*\\((.*?)\\)$")
    public void standard_action_3(String who, String action) throws Throwable {
        Init.getPageFactory().getCurrentPage().takeAction(action);
    }

    @When("^(I|User) \\((.*?)\\) with data$")
    public void standard_action_4(String who, String action, DataTable dataTable) throws Throwable {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Init.getPageFactory().getCurrentPage().takeAction(action, data);
    }

    @When("^(I|User) \\((.*?)\\) \"([^\"]*)\" and input data$")
    public void standard_action_5(String who, String action, String param, DataTable dataTable) throws Throwable {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Init.getPageFactory().getCurrentPage().takeAction(action, param, data);
    }

    @When("^(I|User) \\((.*?)\\) with single line data$")
    public void standard_action_6(String who, String action, DataTable dataTable) throws Throwable {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Init.getPageFactory().getCurrentPage().takeAction(action, data.get(0));
    }

    @When("^(I|User) go to the xm.com$")
    public void go_to_xm(String who) {
        Init.getWebDriver().get(Props.get("app.url"));
//        Init.getIosDriver();
//        try {
//            Init.getDriver().navigate().to(getAppUrl());
//            Init.getDriverExtensions().waitForPageToLoad();
//        } catch (TimeoutException e) {
//            Init.getDriver().navigate().to(getAppUrl());
//            Init.getDriverExtensions().waitForPageToLoad();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}
