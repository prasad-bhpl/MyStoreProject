package com.mystore.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class AccountCreationPageTest extends BasecClass{
	IndexPage indexPage;
	LoginPage loginpage;
	AccountCreationPage accountCreationPage;
	HomePage homepage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(dataProvider="Email", dataProviderClass=DataProviders.class)
	public void verifyCreateAccountPageTest(String email) throws Throwable
	{
		indexPage=new IndexPage();
		loginpage=indexPage.clickOnSignIn();
		//accountCreationPage=loginpage.createNewAccount("apppp@gmail.com");
		accountCreationPage=loginpage.createNewAccount(email);
		boolean result=accountCreationPage.validateAcountCreatePage();
		Assert.assertTrue(result);
		
	}
	@Test(dataProvider="newAcountDetailsData", dataProviderClass=DataProviders.class)
	public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable {
		Log.startTestCase("createAccountTest");
		indexPage= new IndexPage();
		loginpage=indexPage.clickOnSignIn();
		accountCreationPage=loginpage.createNewAccount(hashMapValue.get("Email"));
		accountCreationPage.createAccount(
				hashMapValue.get("Gender"),
				hashMapValue.get("FirstName"),
				hashMapValue.get("LastName"),
				hashMapValue.get("SetPassword"),
				hashMapValue.get("Day"),
				hashMapValue.get("Month"),
				hashMapValue.get("Year"),
				hashMapValue.get("Company"),
				hashMapValue.get("Address"),
				hashMapValue.get("City"),
				hashMapValue.get("State"),
				hashMapValue.get("Zipcode"),
				hashMapValue.get("Country"),
				hashMapValue.get("MobilePhone"));
		homepage=accountCreationPage.validateRegistration();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homepage.getCurrentUrl());
		Log.endTestCase("createAccountTest");
	}
}
