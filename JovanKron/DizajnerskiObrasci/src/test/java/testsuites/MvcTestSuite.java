package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mvc.ApplicationTests;
import mvc.DrawingControllerTests;
import mvc.DrawingFrameTests;
import mvc.DrawingModelTests;
import mvc.DrawingViewTests;

@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
			ApplicationTests.class,
			DrawingControllerTests.class,
			DrawingFrameTests.class,
			DrawingModelTests.class,
			DrawingViewTests.class,
		})
public class MvcTestSuite {

}
