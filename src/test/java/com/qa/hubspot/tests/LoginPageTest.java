package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 :create login page features")
@Feature("US - 501 : create test for login page on hubspot")
public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	SoftAssert softAssert;
	Credentials userCred;

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, description = "verify login page title test...!!!", groups = "sanity")
	@Description("verify Login Page Title Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		System.out.println("login page title is : " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("verify Sign Up Link Test....")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3)
	@Description("verify no account test....")
	@Severity(SeverityLevel.MINOR)
	public void verifyNoAccountText() {
		String text = loginPage.getNoAccountText();
		System.out.println("Text under Hubspot symbol is : " + text);
		Assert.assertEquals(text, AppConstants.NO_ACCOUNT_TEXT);
	}

	@Test(priority = 4)
	@Description("verify email and password label Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyEmailAndPasswordLabelText() {
		softAssert = new SoftAssert();
		String emailLabel = loginPage.getEmailLabel();
		softAssert.assertEquals(emailLabel, AppConstants.EMAIL_LABEL);
		String passwordLabel = loginPage.getPasswordLabel();
		softAssert.assertEquals(passwordLabel, AppConstants.PASS_LABEL);
		softAssert.assertAll();
	}

	@Test(priority = 5)
	@Description("verify show and hide passwoed text Test....")
	@Severity(SeverityLevel.MINOR)
	public void verifyShowAndHidePasswordLinkText() {
		softAssert = new SoftAssert();

		String showPass = loginPage.getShowPasswordLinkText();
		softAssert.assertEquals(showPass, AppConstants.SHOW_PASS_TEXT);

		String hidePass = loginPage.getHidePasswordLinkText();
		softAssert.assertEquals(hidePass, AppConstants.HIDE_PASS_TEXT);

		softAssert.assertAll();
	}

	@Test(priority = 6)
	@Description("verify forgot password Link Test....")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyForgotPasswordLink() {
		Assert.assertTrue(loginPage.checkForgotPasswordLink());
	}

	@Test(priority = 7,enabled = false)
	@Description("verify remember me checkbox Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyRememeberMeCheckBox() {
		boolean chkBox = loginPage.checkRememberMeCheckBoxIsChecked();
		Assert.assertFalse(chkBox);
	}

	@Test(priority = 8,enabled = false)
	@Description("verify login button diabled Test....")
	@Severity(SeverityLevel.MINOR)
	public void verifyLoginButtonIsDisabled() {
		String loginBtn = loginPage.checkIfLoginButtonisDisabled();
		Assert.assertTrue(Boolean.parseBoolean(loginBtn));

	}

	@Test(priority = 9,enabled = false)
	@Description("verify google and sso button Test....")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyGoogleAndSSOBtn() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(loginPage.checkGoogleButton());
		softAssert.assertTrue(loginPage.checkSSOButton());
		softAssert.assertAll();
	}

	@Test(priority = 10,enabled = false)
	@Description("verify footer content Test....")
	@Severity(SeverityLevel.MINOR)
	public void verifyFooterText() {
		Assert.assertTrue(loginPage.checkfooterTextContent().contains(AppConstants.FOOTER_TEXT));
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {

		Object data[][] = { { "test1111@gmail.com", "test123456" }, { "test2@gmail.com", "  " }, { "  ", "test12345" },
				{ "   ", "   " } };

		return data;
	}

	@Test(priority = 11, dataProvider = "getLoginInvalidData", enabled = false)
	@Description("verify login with incorrect username and password Test....")
	@Severity(SeverityLevel.CRITICAL)
	public void login_InvalidTestCases(String username, String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);

		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

	@Test(priority = 12, groups = "Regression", enabled = false)
	@Description("verify login test...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
