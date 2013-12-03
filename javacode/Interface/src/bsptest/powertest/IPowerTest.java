package bsptest.powertest;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;

interface IPowerTest
{
	void PlayGame() throws IOException, UiObjectNotFoundException, RemoteException;
	void Music() throws UiObjectNotFoundException,  RemoteException, Throwable;
	void Video() throws UiObjectNotFoundException, IOException, RemoteException;
	void ReadBook() throws IOException, UiObjectNotFoundException;
	void Weibo() throws IOException, UiObjectNotFoundException;
	void Call() throws IOException, UiObjectNotFoundException, RemoteException;
	void Brower() throws IOException, UiObjectNotFoundException;
	void Camera() throws IOException, UiObjectNotFoundException;
	void ContinueBrower() throws IOException, UiObjectNotFoundException, ParserConfigurationException, SAXException; 
	void CrashBug();

}
