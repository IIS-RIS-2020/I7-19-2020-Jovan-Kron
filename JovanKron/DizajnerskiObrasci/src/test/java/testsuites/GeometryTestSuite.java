package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import geometry.CircleTests;
import geometry.DonutTests;
import geometry.HexagonAdapterTests;
import geometry.LineTests;
import geometry.PointTests;
import geometry.RectangleTests;

@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
			CircleTests.class,
			DonutTests.class,
			HexagonAdapterTests.class,
			LineTests.class,
			PointTests.class,
			RectangleTests.class
		})
public class GeometryTestSuite {
	
}
