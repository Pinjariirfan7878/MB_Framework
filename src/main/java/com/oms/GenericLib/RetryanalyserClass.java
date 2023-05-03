package com.oms.GenericLib;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryanalyserClass implements IRetryAnalyzer {


	int count=0;
	int trycount=3;

	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<trycount)
		{
			count++;
			return true;
		}
		return false;
	}
}
