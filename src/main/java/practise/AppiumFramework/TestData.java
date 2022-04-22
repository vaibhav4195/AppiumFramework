package practise.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name="InputData")
	public Object[][] getDataForEditField() {
//		send 2 sets of data "hello" "Vai@357%"
		Object [][] obj = new Object[][] {
			{"Hello"},{"Vai@357%"}
		};
		return obj;
	}

}
