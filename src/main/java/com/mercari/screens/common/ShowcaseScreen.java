package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class ShowcaseScreen {
    private final UIHelper app;
    public boolean isProductListDisplayed;



    public ShowcaseScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id,'tvTitle')]"));}
    public UIHelper rotateLoadingIcon() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id, 'rotateloading')]"));}
    public UIHelper N_Product_Number(int i) throws Exception{ return app.setBy(By.xpath("//*[@resource-id='com.mercari.international:id/ListView']/android.widget.RelativeLayout["+i+"]/android.widget.FrameLayout[1]"));}
    public UIHelper firstProduct() throws Exception{ return app.setBy(By.xpath("//*[@resource-id='com.mercari.international:id/ListView']/android.widget.RelativeLayout[1]"));}
    public UIHelper productList() throws Exception{return app.setBy(By.id("productImage"));}
    public UIHelper noProducts() throws Exception{ return app.setBy(By.xpath("//*[contains(@text,'No Products')]"));}
    public UIHelper cartBtn() throws Exception {return app.setBy(By.xpath("//android.widget.ImageButton[contains(@resource-id, 'cartLogo')]"));}

    @Step("Verify screen title : 'Show Case' ")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify user is navigated to Show Case screen");
        return pageTitle().isElementPresent(10) && pageTitle().getText().equals("Show Case");
    }

    @Step("Verify user can see Product list on Show Case screen")
    public void verifyUserCanSeeProductsList() throws Exception {
        System.out.println("Verify user can see Product list on Show Case screen");
        if (productList().isElementPresent(5)) {
            Assert.assertTrue(productList().isElementPresent(), "Product list is not displayed");
            isProductListDisplayed = true;
            System.out.println("Product list is displayed");
        }
        else if (noProducts().isElementPresent(5)){
            System.out.println("Verified : NO Products are displayed in the list");
            isProductListDisplayed = false;
        }
        else System.out.println("Products are displayed but not identified. Please check issue manually once");
    }

    @Step("Select first Product displayed in the list on Show Case screen")
    public void selectFirstProduct() throws Exception {
        System.out.println("Select first Product displayed in the list on Show Case screen");
        if (isProductListDisplayed) {
            System.out.println("Select first Product from the list when the flag is: " + isProductListDisplayed);
            firstProduct().click();
            rotateLoadingIcon().waitForDisappear();
        }
        else System.out.println("Product list is empty");
    }

    @Step("Click on Product number=N from the Product list")
    public void clickOnProductNumber(int N) throws Exception {
        System.out.println("Click on Product number = "+N+" from the Product list");
        if (isProductListDisplayed) {
            N_Product_Number(N).click();
            rotateLoadingIcon().waitForDisappear();
        }
        else System.out.println("Product list is empty");
    }

    @Step("Verify Cart Logo displayed on Show Case screen")
    public boolean verifyCartLogo() throws Exception {
        return cartBtn().isElementPresent(10);
    }

    @Step("Click on Cart Logo displayed on Show Case screen")
    public void clickCartLogo() throws Exception {
        System.out.println("Click on Cart Logo displayed on Show Case screen");
        if (cartBtn().isElementPresent(5))
            cartBtn().click();
        else System.out.println("Cart Logo is NOT displayed on Show Case screen");
    }


}