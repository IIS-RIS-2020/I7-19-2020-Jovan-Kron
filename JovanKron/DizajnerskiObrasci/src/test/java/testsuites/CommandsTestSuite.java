package testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import commands.CmdAddTests;
import commands.CmdBringToBackTests;
import commands.CmdBringToFrontTests;
import commands.CmdRemoveTests;
import commands.CmdSelectTests;
import commands.CmdToBackTests;
import commands.CmdToFrontTests;
import commands.CmdUpdateTests;

@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
			CmdAddTests.class,
			CmdBringToBackTests.class,
			CmdBringToFrontTests.class,
			CmdRemoveTests.class,
			CmdSelectTests.class,
			CmdToBackTests.class,
			CmdToFrontTests.class,
			CmdUpdateTests.class,
		})
public class CommandsTestSuite {
	
}
