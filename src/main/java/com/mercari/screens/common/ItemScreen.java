package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class ItemScreen {
    private final UIHelper app;
    public String ProductSelected;

    public ItemScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//android.widget.ImageButton[contains(@resource-id, 'ratingStar')]"));}
    public UIHelper addToCart() throws Exception {return app.setBy(By.xpath("//*[@text='Add to Cart']"));}
    public UIHelper productName() throws Exception { return app.setBy(By.xpath("//*[contains(@resource-id,'productId-')]"));}

    @Step("Verify if user is redirected on Item screen once user clicks on the specific Product")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify if user is redirected on Item screen once user clicks on the specific Product");
        return pageTitle().isElementPresent(10);
    }

    @Step("Click 'Add to Cart' button on Item screen")
    public void clickAddToCartBtn() throws Exception {
        System.out.println("Click 'Add to Cart' button on Item screen");
        if(!addToCart().isElementPresent()) {
            app.androidScrollToText("Subscribe");
            addToCart().scrollToElementDownSide();
        }
        Assert.assertTrue(addToCart().isElementPresent(),"'Add to Cart' button is NOT displayed");
    }

    public String getProductName() throws Exception {
        ProductSelected = productName().getText();
        return ProductSelected;
    }
}
