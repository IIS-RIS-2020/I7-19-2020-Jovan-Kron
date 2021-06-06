package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dialogs.DlgCircleTests;
import dialogs.DlgCommandsTests;
import dialogs.DlgDonutTests;
import dialogs.DlgHexagonTests;
import dialogs.DlgLineTests;
import dialogs.DlgPointTests;
import dialogs.DlgRectangleTests;

@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
			DlgCircleTests.class,
			DlgCommandsTests.class,
			DlgDonutTests.class,
			DlgHexagonTests.class,
			DlgLineTests.class,
			DlgPointTests.class,
			DlgRectangleTests.class,
		})
public class DialogsTestSuite {

}
