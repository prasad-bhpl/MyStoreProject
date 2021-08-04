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
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.SearchResultPage;

/**
 * @author prasa
 *
 */
public class AddToCartPageTest extends BasecClass {
	IndexPage indexPage;
	LoginPage loginpage;
	HomePage homepage;
	SearchResultPage searchresultPage;
	AddToCartPage addtocartpage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test(groups = {"Regression","Sanity"}, dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void addToCartTest(String productName, String qty, String size) throws InterruptedException
	{
		indexPage=new IndexPage();
		searchresultPage=indexPage.searchProduct(productName);
		addtocartpage=searchresultPage.clickOnProduct();
		addtocartpage.enterQuantity(qty);
		addtocartpage.enterSize(size);
		addtocartpage.clickOnAddToCart();
		boolean result=addtocartpage.validateAddToCart();
		Assert.assertTrue(result);
	}

}
