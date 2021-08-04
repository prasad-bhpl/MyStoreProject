/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import com.mystore.Base.BasecClass;
import com.mystore.pageobjects.IndexPage;


/**
 * @author prasa
 *
 */
public class IndexPageTest extends BasecClass{

IndexPage indexPage;

@Parameters("browsername")
@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
public void setup(String browsername) {
	launchapp(browsername);
}

@AfterMethod(groups = {"Smoke","Sanity","Regression"})
public void tearDown() {
	getDriver().quit();
}

@Test
public void veirfyLogo()
{
	indexPage=new IndexPage();
	boolean result=indexPage.validateLogo();
	Assert.assertTrue(result);
}
@Test
public void verifyTitle()
{
	String actTitle=indexPage.getmyStoreTitle();
	Assert.assertEquals(actTitle, "My Store");
	
}
}
