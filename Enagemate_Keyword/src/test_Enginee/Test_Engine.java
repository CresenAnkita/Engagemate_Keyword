package test_Enginee;


import java.awt.AWTException;

import org.testng.annotations.Test;

import TestRunner.Test_runner;

public class Test_Engine {
public Test_runner test;
	


	@Test(priority = 1)
	public void Engagemate() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("Engagemate");
		Thread.sleep(2000);
	}

}
