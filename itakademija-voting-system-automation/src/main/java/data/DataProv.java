package data;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProv {
	
	@DataProvider(name="Provider")
	public Object[][] provider(ITestContext context)
	{
	 Map<String, String> testParams = context.getCurrentXmlTest().getLocalParameters();
	// Map<String, String> suiteParams=context.getCurrentXmlTest().getSuite().getParameters();

	 return new Object[][]{{testParams.get("param1"), testParams.get("param2")}};
	}

}
