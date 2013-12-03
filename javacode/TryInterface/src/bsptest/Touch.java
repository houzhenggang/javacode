package bsptest;

import android.graphics.Point;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Touch extends UiAutomatorTestCase
{
	public void test_Touch()
	{
		Point[] point = new Point[5];
		point[0] = new Point(592, 635);
		point[1] = new Point(520, 660);
		point[2] = new Point(436, 690);
		point[3] = new Point(362, 728);
		point[4] = new Point(262, 783);	
		getUiDevice().swipe(point, 10); 	
		/*getUiDevice().swipe(592, 635, 520, 660, 1);
		sleep((long) 10.0);
		getUiDevice().swipe(520, 660, 436, 690, 1);
		sleep((long) 10.0);
		getUiDevice().swipe(436, 690,362, 728, 1);
		sleep((long) 10.0);
		getUiDevice().swipe(362, 728, 262, 783, 1);
		sleep((long) 10.0);	*/
	}

}
