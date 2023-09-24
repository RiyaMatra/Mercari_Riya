package com.mercari.screens.common;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utlities.UIHelper;

public class LoginScreen {
    private final UIHelper app;

    public LoginScreen(UIHelper app){
        this.app= app;
    }

    public UIHelper username() throws Exception {return app.setBy(By.xpath("//*[@value='Enter username']"));}
    public UIHelper password() throws Exception {return app.setBy(By.xpath("//*[@id='passwd']"));}
    public UIHelper login_btn() throws Exception {return app.setBy(By.xpath("//*[@id='login_btn']"));}
    public UIHelper loginViaEmail() throws Exception {return app.setBy(By.xpath("//*[@id='email_Login']"));}
    public UIHelper pageTitle() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id,'tvTitle') and  @text ='Login']"));}
    public UIHelper rotateLoadingIcon() throws Exception {return app.setBy(By.xpath("//*[contains(@resource-id, 'rotateloading')]"));}

    //METHODS
    public void loginWithCredentials(String un, String pw ) throws Exception {
        System.out.println("Login with username : "+un+" ");
        if(username().isElementPresent(10)) {
            username().sendkeys(un);
            password().sendkeys(pw);
            app.hideKeyboard();
            login_btn().click();
            rotateLoadingIcon().waitForDisappear();
            System.out.println("Login is successful");
        }
        else System.out.println("Login Page not found");
    }

    @Step("Verify screen title : 'Login'")
    public boolean verifyScreen() throws Exception {
        System.out.println("Verify user is on Login screen");
        return pageTitle().isElementPresent(10);
    }

}
