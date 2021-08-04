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
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;

/**
 * @author prasa
 *
 */
public class SearchResultPageTest extends BasecClass{

	IndexPage indexPage;
	SearchResultPage searchresultPage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabilityTest(String product) throws InterruptedException
	{
		indexPage=new IndexPage();
		searchresultPage=indexPage.searchProduct(product);
		boolean result=searchresultPage.isProductAvailable();
		Assert.assertTrue(result);
	}
}
