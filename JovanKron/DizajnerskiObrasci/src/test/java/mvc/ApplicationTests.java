package mvc;

import org.junit.Test;

public class ApplicationTests {

	@Test(expected = Test.None.class /* no exception expected */)
	public void testInvokeExpectedNoErrors() {
		Application.main(null);
	}
	
}
