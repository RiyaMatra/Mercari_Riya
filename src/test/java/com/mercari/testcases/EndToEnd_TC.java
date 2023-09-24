package com.mercari.testcases;

import com.mercari.screens.common.*;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utlities.UIHelper;

public class EndToEnd_TC {
    UIHelper app;
    LoginScreen loginScreen;
    ShowcaseScreen showcaseScreen;
    ItemScreen itemScreen;
    CartScreen cartScreen;
    AddressScreen addressScreen;
    ConfirmPurchaseScreen confirmPurchaseScreen;
    EnterShippingAddressScreen enterShippingAddressScreen;
    String productName1;
    String productName2;
    String un, pw;
    String addressLine, city, pincode, state;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        app                         = new UIHelper();
        loginScreen                 = new LoginScreen(app);
        showcaseScreen              = new ShowcaseScreen(app);
        itemScreen                  = new ItemScreen(app);
        cartScreen                  = new CartScreen(app);
        addressScreen               = new AddressScreen(app);
        confirmPurchaseScreen       = new ConfirmPurchaseScreen(app);
        enterShippingAddressScreen  = new EnterShippingAddressScreen(app);
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() throws InterruptedException {
        app.quitDriver();
    }

    @Link("JIRA LINK OF TC")
    @Test(priority=1, description="Launch the app & login successfully")
    public void verifyAppLogin() throws Exception {
        System.out.println("---Start of the test---");
        System.out.println("Launch the app & verify Login screen");
        un = app.prop.getProperty("loginAccount");
        pw = app.prop.getProperty("loginPassword");
        Assert.assertTrue(loginScreen.verifyScreen(), "Login screen is NOT displayed");
        loginScreen.loginWithCredentials(un, pw);
        System.out.println("User has logged in successfully");
        System.out.println("Verify if user is redirected from Login -> Showcase screen successfully");
        Assert.assertTrue(showcaseScreen.verifyScreen(), "User is not redirected to Show Case screen successfully");
    }

    @Test(priority=2, dependsOnMethods="verifyAppLogin", description="Verify Show Case screen & add Products to the Cart")
    public void addProductsToCart() throws Exception {
        System.out.println("Verify Show Case screen");
        showcaseScreen.verifyUserCanSeeProductsList();
        showcaseScreen.selectFirstProduct();
        System.out.println("Verify Item screen");
        Assert.assertTrue(itemScreen.verifyScreen(), "User is not landed on Item screen");
        productName1 = itemScreen.getProductName();
        itemScreen.clickAddToCartBtn();
        System.out.println("User has selected product = " + productName1);
        app.androidClickBack();
        Assert.assertTrue(showcaseScreen.verifyScreen(), "User is not redirected to Show Case screen successfully");
        showcaseScreen.clickOnProductNumber(2);
        Assert.assertTrue(itemScreen.verifyScreen(), "User is not landed on Item screen");
        productName2 = itemScreen.getProductName();
        itemScreen.clickAddToCartBtn();
        System.out.println("User has selected product = " + productName2);
        app.androidClickBack();
        Assert.assertTrue(showcaseScreen.verifyCartLogo(),"Cart logo is NOT displayed on Showcase screen");
    }

    @Test(priority=3, dependsOnMethods="addProductsToCart", description="Verify Total no. of products are correctly displayed in the Cart")
    public void verifyCart() throws Exception {
        System.out.println("Verify Total no. of products are correctly displayed in the Cart");
        showcaseScreen.clickCartLogo();
        Assert.assertTrue(cartScreen.verifyScreen(), "User is not redirected to Cart screen");
        System.out.println("Verify user is able to see products added to the Cart");
        Assert.assertTrue(cartScreen.cartProducts(productName1).isElementPresent(5), productName1 + " is NOT added to cart");
        Assert.assertTrue(cartScreen.cartProducts(productName2).isElementPresent(5), productName2 + " is NOT added to cart");
        System.out.println("Verify Total no. of items in the cart are displayed correctly");
        cartScreen.verifyProductsCountInCart();
        cartScreen.getSumOfAllIndividualProducts();
        cartScreen.getTotalPurchaseAmountOnCart();
        System.out.println("Verify IF 'Total Purchase Amount' displayed = Sum of Individual prices of all products added in the Cart");
        Assert.assertEquals(cartScreen.totalPurchaseAmountOnCart, cartScreen.sumOfIndividualProductPrices, "Sum(Individual products) != Total Purchase Amount displayed on Cart");
        cartScreen.clickProceedCTA();
        System.out.println("Verify if user is redirected from Cart -> Address screen successfully");
        addressScreen.verifyScreen();
    }

    @Test(priority=4, dependsOnMethods="verifyCart", description="Verify if user can add new Shipping Address")
    public void verifyUserCanAddNewAddress() throws Exception {
        System.out.println("Verify if user can add new Shipping Address");
        addressScreen.clickAddNewAddress();
        Assert.assertTrue(enterShippingAddressScreen.verifyScreen(), "User is not redirected on 'Enter Shipping Address' screen");
        enterShippingAddressScreen.verifyScreen();
        addressLine = app.prop.getProperty("addressLine");
        city = app.prop.getProperty("city");
        pincode = app.prop.getProperty("pincode");
        state = app.prop.getProperty("state");
        enterShippingAddressScreen.enterNewAddress(addressLine, city, pincode, state);
        enterShippingAddressScreen.clickUseThisAddressCTA();
    }

    @Test(priority=5, description="Verify if Order is placed successfully")
    public void verifyOrderIsPlaced() throws Exception {
        System.out.println("Verify if Order is placed successfully");
        Assert.assertTrue(confirmPurchaseScreen.verifyScreen(),"User is not redirected on 'Confirm Purchase' screen");
        confirmPurchaseScreen.verifyScreen();
        confirmPurchaseScreen.clickConfirmCTA();
        Assert.assertTrue(confirmPurchaseScreen.verifyToastMessage(),"Order is not placed successfully. Please Check.");
        System.out.println("Order success toast messaged : Verified");
    }

}
