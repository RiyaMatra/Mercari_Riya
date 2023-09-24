package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utlities.UIHelper;

public class ConfirmPurchaseScreen {
    private final UIHelper app;

    public ConfirmPurchaseScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//*[@text='Confirm Purchase']"));}
    public UIHelper rotateLoadingIcon() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id, 'rotateloading')]"));}
    public UIHelper confirmBtn() throws Exception {return app.setBy(By.xpath("//*[@text='Confirm']"));}
    public UIHelper orderSuccess_ToastMsg() throws Exception {return app.setBy(By.xpath("//*[@text='Your order is placed']"));}
    @Step("Verify screen title = 'Confirm Purchase'")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify screen title = 'Confirm Purchase'");
        rotateLoadingIcon().waitForDisappear();
        return pageTitle().isElementPresent();
    }

    @Step("Click Confirm CTA ")
    public void clickConfirmCTA() throws Exception {
        System.out.printf("Click Confirm CTA");
        confirmBtn().click();
    }

    @Step("Click Confirm CTA ")
    public boolean verifyToastMessage() throws Exception {
        return  orderSuccess_ToastMsg().isElementPresent(10);
    }

}
