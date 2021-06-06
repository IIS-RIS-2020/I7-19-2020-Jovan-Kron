package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import strategy.FileManagerTests;
import strategy.LogFileTests;
import strategy.SerializableFileTests;

@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
			FileManagerTests.class,
			LogFileTests.class,
			SerializableFileTests.class
		})
public class StrategyTestSuite {

}
