package com.qa.hubspot.page;

import org.openqa.selenium.By;

import com.qa.hubspot.base.BasePage;

public class DealsPage extends BasePage{
	
	By dealsHeader=By.xpath("//span[text()='Deals']");

	public DealsPage() {
		System.out.println("Deals page const..........");
	}
	
	
}
