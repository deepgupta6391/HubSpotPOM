package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials userCred;

	@BeforeTest(alwaysRun = true)
	@Parameters(value = { "browser" })
	public void setUp(String browser) throws InterruptedException {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();

		if (browser.equals(null)) {
			browserName = prop.getProperty("browser");
		} else {
			browserName = browser;
		}
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		contactsPage = homePage.goToContactsPage();
	}

	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title is : " + title);
		Assert.assertEquals(title, "Contacts");
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
		return data;
	}

	@Test(priority = 2, dataProvider = "getContactsTestData")
	public void createContactsTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createSingleContact(email, firstName, lastName, jobTitle);
		// contactsPage.createMultipleContacts(email, firstName, lastName, jobTitle);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
