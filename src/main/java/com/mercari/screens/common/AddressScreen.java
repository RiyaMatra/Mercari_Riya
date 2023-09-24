package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class AddressScreen {
    private final UIHelper app;

    public AddressScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//*[@text='Select your Delivery address']"));}
    public UIHelper addNewAddress() throws Exception {return app.setBy(By.id("Add_new_address"));}
    public UIHelper rotateLoadingIcon() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id, 'rotateloading')]"));}

//METHODS
    @Step("Verify screen title = 'Select your Delivery address'")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify screen title = 'Select your Delivery address'");
        return pageTitle().isElementPresent(5);
    }

    @Step("Verify screen title = 'Select your Delivery address'")
    public void clickAddNewAddress() throws Exception {
        Assert.assertTrue(addNewAddress().isElementPresent(5), "'Add New Address' CTA is NOT displayed");
        addNewAddress().click();
        rotateLoadingIcon().waitForDisappear();
    }

}
