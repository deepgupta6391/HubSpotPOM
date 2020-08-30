package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
	}

	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}

	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception got occurred while getting the page title....");
		}
		return null;
	}

	/**
	 * this element is used to create web element on the basis of by locator
	 * 
	 * @param locator
	 * @return element
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			// if (waitForElementPresent(locator))
			element = driver.findElement(locator);
			if (highlightElement) {
				jsUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the web element.....");
		}
		return element;
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occurred while clicking on the web element....");
		}
	}

	public void doSendKeys(By locator, String value) {
		try {
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception got occurred while enetering value in a field.....");
		}
	}

	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception occured while checking for webelement display....");
		}
		return false;
	}

	public boolean doIsSelected(By locator) {
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception occured while checking for webelement is checked....");
		}
		return false;
	}

	public String doGetText(By locator) {
		try {
			return getElement(locator).getText().trim();
		} catch (Exception e) {
			System.out.println("some exception got occurred while getting the text from a webelement....");
		}
		return null;
	}

	public String getAttributeTextAfterTrim(By locator) {
		try {
			return getElement(locator).getAttribute("textContext").trim();
		} catch (Exception e) {
			System.out.println(
					"some exception got occurred while getting the text from a webelement using getAttribute....");
		}
		return null;
	}

	public String doGetAttribute(By locator, String attribute) {
		try {
			return getElement(locator).getAttribute(attribute);
		} catch (Exception e) {
			System.out.println("some exception got occurred while getting the text of attribute....");
		}
		return null;
	}

}
