package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DialogsTestSuite.class,
	StrategyTestSuite.class
	})
public class PartialInteractionTestSuite {

}