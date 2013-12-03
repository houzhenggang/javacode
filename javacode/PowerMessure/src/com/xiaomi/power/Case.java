package com.xiaomi.power;

import java.io.IOException;

import com.android.uiautomator.core.UiObjectNotFoundException;

interface Case {
	void GameTest() throws UiObjectNotFoundException, IOException;
	void CallMotest() throws UiObjectNotFoundException, IOException;


}
