package xjh;


import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTest {

    public static Test suite() {
            TestSuite suite = new TestSuite();
            
             //逐一添加testCase类 
            suite.addTestSuite(haha.class);
            return suite;
    }

    public static void main(String args[]){ 
    	junit.textui.TestRunner.run(AllTest.suite());
	}
}