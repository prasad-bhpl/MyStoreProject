/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author prasa
 *
 */
public class HomePageTest extends BasecClass {
	IndexPage indexPage;
	LoginPage loginpage;
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
	
	@Test(groups = "Smoke")
	public void wishListTest()
	{
		indexPage=new IndexPage();
		loginpage=indexPage.clickOnSignIn();
		homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean result=homepage.validateMyWishList();
		Assert.assertTrue(result);
	}
	@Test
	public void orderHistoryDetailsTest()
	{
		indexPage=new IndexPage();
		loginpage=indexPage.clickOnSignIn();
		homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean result=homepage.validateorderHistory();
		Assert.assertTrue(result);
	}

}
