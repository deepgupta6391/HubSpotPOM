package com.qa.hubspot.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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


@Epic("Epic - 102 :create Home page features")
@Feature("US - 502 : create test for Home page on hubspot")
public class HomePageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}

	@Test(priority = 1,groups = {"sanity","Regression"})
	@Description("verify Home Page Title Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is : " + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	@Description("verify Home Page Header Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is : " + header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	@Description("verify logged in user Test....")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyLoggedInUserTest() {
		String accountName = homePage.getLogedInUserAccountName();
		System.out.println("logged in user account name : " + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}

	@Test(priority = 4)
	@Description("verify header contents Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHeaderContents() {
		List<String> actualArr = homePage.getHeaderContentsList();
		System.out.println("Actual list of contents : " + actualArr);
		System.out.println("Extected list of contents : " + Arrays.asList(AppConstants.HOME_PAGE_HEADER_CONTENTS));
		Assert.assertEquals(actualArr, Arrays.asList(AppConstants.HOME_PAGE_HEADER_CONTENTS));
	}

	@Test(priority = 5)
	@Description("verify link contents under contacts Test....")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLinksUnderContacts() {
		List<String> valuesUnderContacts = homePage.getLinksUnderContacts();
		System.out.println("Actual list of contents under contacts: " + valuesUnderContacts);
		System.out.println("Expected list of contents : " + Arrays.asList(AppConstants.LINKS_UNDER_CONTACTS));
		Assert.assertEquals(valuesUnderContacts, Arrays.asList(AppConstants.LINKS_UNDER_CONTACTS));
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
