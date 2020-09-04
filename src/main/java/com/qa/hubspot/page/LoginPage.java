package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;

	// 1.locators - By
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButon = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By dontHaveAccText = By.xpath("//i18n-string[text()=\"Don't have an account?\"]");
	By emailLabelText = By.id("UIFormControl-label-2");
	By pwdLabelText = By.xpath("//label[@for='password']");
	By showPasswordLink = By.xpath("//button[contains(@class,'private-button__link private-button--default')]/span");
	By forgotPassLink = By.linkText("Forgot my password");
	By googleBtn = By.className("google-sign-in");
	By ssoBtn = By.id("ssoBtn");
	By footerText = By.className("copyright");
	By rememberMeCheckBox = By.className("private-checkbox__dash");
	By loginErrorText = By.xpath("//div[@class='private-alert__inner']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
	}

	// page actions:

	public String getPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getPageTitleUsingJS() {
		return jsUtil.getTitleJS();
	}

	public boolean checkSignUpLink() {
		elementUtil.waitForElementVisible(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}

	public String getNoAccountText() {
		return elementUtil.doGetText(dontHaveAccText);
	}

	public String getEmailLabel() {
		// return driver.findElement(emailLabelText).getText();
		return elementUtil.doGetText(emailLabelText);
	}

	public String getPasswordLabel() {
		// return driver.findElement(pwdLabelText).getText();
		return elementUtil.doGetText(pwdLabelText);
	}

	public String getShowPasswordLinkText() {
		// return driver.findElement(showPasswordLink).getText();
		return elementUtil.doGetText(showPasswordLink);
	}

	public String getHidePasswordLinkText() {
		// driver.findElement(showPasswordLink).click();
		elementUtil.doClick(showPasswordLink);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return driver.findElement(showPasswordLink).getText();
		return elementUtil.doGetText(showPasswordLink);
	}

	public boolean checkForgotPasswordLink() {
		// return driver.findElement(forgotPassLink).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPassLink);
	}

	public boolean checkRememberMeCheckBoxIsChecked() {
		// return driver.findElement(rememberMeCheckBox).isSelected();
		return elementUtil.doIsSelected(rememberMeCheckBox);
	}

	public String checkIfLoginButtonisDisabled() {
		// String text=driver.findElement(loginButon).getAttribute("aria-disabled");
		return elementUtil.doGetAttribute(loginButon, "aria-disabled");
	}

	public boolean checkGoogleButton() {
		// return driver.findElement(googleBtn).isDisplayed();
		return elementUtil.doIsDisplayed(googleBtn);
	}

	public boolean checkSSOButton() {
		// return driver.findElement(ssoBtn).isDisplayed();
		return elementUtil.doIsDisplayed(ssoBtn);
	}

	public String checkfooterTextContent() {
		// String text= driver.findElement(footerText).getText();
		return elementUtil.doGetText(footerText);
	}

	public HomePage doLogin(Credentials userCred) {
		elementUtil.waitForElementPresent(emailId);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButon);

		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage() {
		return elementUtil.doIsDisplayed(loginErrorText);
	}
}
