package com.mercari.screens.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class CartScreen {
    private final UIHelper app;
    public double totalPurchaseAmountOnCart;
    public double sumOfIndividualProductPrices=0.00;

    public CartScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper screenTitle() throws Exception {return app.setBy(By.id("toolbar_title"));}
    public UIHelper cartProducts(String productName) throws Exception {return app.setBy(By.xpath("//*[@text='"+productName+"']"));}
    public UIHelper productList() throws Exception {return app.setBy(By.id("productImage"));}
    public UIHelper individualProductPrice() throws Exception {return app.setBy(By.id("productPrice"));}
    public UIHelper purchaseAmountOnCart() throws Exception {return app.setBy(By.id("totalAmountLbl"));}
    public UIHelper proceedBtn() throws Exception { return app.setBy(By.xpath("//*[contains(@text,'Proceed')]"));}

    //METHODS
    @Step("Verify screen title = 'Shopping Bag'")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify screen title = 'Shopping Bag'");
        return screenTitle().isElementPresent(10) && screenTitle().getText().equals("Shopping Bag");
    }

    @Step("Verify products are added to cart")
    public void verifyProductsCountInCart() throws Exception {
        System.out.println("Verify products are added to cart");
        int size = productList().getAll().size();
        System.out.println("Total products added to cart is = "+size);
        Assert.assertEquals(size, 2,"Both products are NOT added successfully");
    }

    @Step("Get sum of all individual Products")
    public double getSumOfAllIndividualProducts() throws Exception {
        System.out.println("Get sum of all individual Products");
        int size = individualProductPrice().getAll().size();
        System.out.println("Total sum needs to be calculated for "+size+" products");
        for(int i =0; i<size; i++) {
            String $Price = individualProductPrice().getAll().get(i).getText();
            $Price = $Price.replaceAll("[$]","");
            double price = Double.parseDouble($Price);
            System.out.println("Price of product "+i+" = "+price);
            sumOfIndividualProductPrices = sumOfIndividualProductPrices + price;
        }
        System.out.println("Total Price Of "+size+" Individual Product Prices = "+sumOfIndividualProductPrices);
        return sumOfIndividualProductPrices;
    }

    @Step("Verify Total Purchase Amount displayed on Cart")
    public double getTotalPurchaseAmountOnCart() throws Exception {
        System.out.println("Verify Total Purchase Amount displayed on Cart");
        totalPurchaseAmountOnCart = Double.parseDouble(purchaseAmountOnCart().getText().replaceAll("[$] ",""));
        System.out.println("Total Purchase Amount displayed on Cart = "+totalPurchaseAmountOnCart);
        return totalPurchaseAmountOnCart;
    }

    @Step ("Click Proceed CTA")
    public void clickProceedCTA() throws Exception {
        System.out.println("Click Proceed CTA");
        if(!proceedBtn().isElementPresent())
            proceedBtn().scrollToElementDownSide();
        else proceedBtn().click();
    }

}
