package com.qa.hubspot.util;

import java.util.List;

public class AppConstants {

	// **********************TimeOuts*************************
	public static int DEFAULT_TIME_OUT = 20;

	// **********************Page Constants**************************

	// ======================LOGIN PAGE======================
	public final static String LOGIN_PAGE_TITLE = "HubSpot Login";
	public final static String NO_ACCOUNT_TEXT = "Don't have an account?";
	public final static String EMAIL_LABEL = "Email address";
	public final static String PASS_LABEL = "Password";
	public final static String SHOW_PASS_TEXT = "Show Password";
	public final static String HIDE_PASS_TEXT = "Hide Password";
	public final static String FOOTER_TEXT = "All Rights Reserved.";

	// ======================Home Page==============================
	public final static String HOME_PAGE_TITLE = "Reports dashboard";
	public final static String HOME_PAGE_HEADER = "Sales Dashboard";
	public final static String[] HOME_PAGE_HEADER_CONTENTS = { "Contacts", "Conversations", "Marketing", "Sales",
			"Service", "Automation", "Reports" };
	public final static String[] LINKS_UNDER_CONTACTS = { "Contacts", "Companies", "Activity Feed", "Lists" };

	// =======================Contacts Page===========================
	public final static String CONTACTS_SHEET_NAME = "contacts";
}