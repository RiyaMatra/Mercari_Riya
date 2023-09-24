package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class EnterShippingAddressScreen {
    private final UIHelper app;

    public EnterShippingAddressScreen(UIHelper app){
        this.app= app;
    }
    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//*[@text='Enter Shipping address']"));}
    public UIHelper addressLine() throws Exception {return app.setBy(By.id("address_line1"));}
    public UIHelper enterCity() throws Exception {return app.setBy(By.id("city"));}
    public UIHelper enterPincode() throws Exception {return app.setBy(By.id("pincode"));}
    public UIHelper selectedState(String defaultStateName) throws Exception {return app.setBy(By.xpath("//*[@text='"+defaultStateName+"']"));}
    public UIHelper stateDD() throws Exception{return app.setBy(By.xpath("//*[contains(@resource-id,'location_selector')]"));}
    public UIHelper selectStateFromList(String stateName) throws Exception {return app.setBy(By.xpath("//*[@text='"+stateName+"']"));}
    public UIHelper useThisAddressCTA() throws Exception {return app.setBy(By.id("submit"));}

    @Step("Verify screen title = 'Enter Shipping address'")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify screen title = 'Enter Shipping  address'");
        return pageTitle().isElementPresent(5);
    }

    @Step("Enter a new address for the Order delivery")
    public void enterNewAddress(String addressLine, String city, String pincode, String state) throws Exception {
        System.out.println("Please enter a new address for the Order delivery");
        addressLine().sendkeys(addressLine);
        enterCity().sendkeys(city);
        enterPincode().sendkeys(pincode);
        selectState(state);
    }

    @Step("Select Region {state name}")
    public void selectState(String state) throws Exception {
        System.out.println("Select Region "+state);
        if(!selectedState(state).isElementPresent(5)) {
            stateDD().tapClick();
            selectStateFromList(state).scrollSlowly(UIHelper.ScrollDirection.DOWN);
            selectStateFromList(state).click();
        }
    }

    @Step("Click 'Use This Address' CTA ")
    public void clickUseThisAddressCTA() throws Exception {
        System.out.printf("Click 'Use This Address' CTA");
        useThisAddressCTA().click();
    }


}
