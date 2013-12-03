package com.xiaomi.bsptest;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Other extends Activity
{
  private TextView mTextView01;
  private ImageView mImageView01;
  
  /* LayoutInflater����@���s��AlertDialog���� */
  private LayoutInflater mInflater01;
  
  /* ��J���ꪺView */
  private View mView01;
  private EditText mEditText01,mEditText02;
  
  /* menu�ﶵidentifier�A�ΥH�ѧO�ƥ� */
  static final private int MENU_ABOUT = Menu.FIRST;
  static final private int MENU_EXIT = Menu.FIRST+1;
  private Handler mHandler01 = new Handler();
  private Handler mHandler02 = new Handler();
  private Handler mHandler03 = new Handler();
  private Handler mHandler04 = new Handler();
  /* ����User�R��P�_��Counter */
  private int intCounter1, intCounter2;
  /* ����FadeIn�PFade Out��Counter */
  private int intCounter3, intCounter4;
  /* ����`�Ǵ����I����ID��Counter  */
  private int intDrawable=0;
  /* �W�@��User���ʧ@��Time Stamp */
  private Date lastUpdateTime;
  /* �p��User�@�X��S���ʧ@ */
  private long timePeriod;
  /* �R��W�Ln��N�۰ʶi�J�ù��O�@ */
  private float fHoldStillSecond = (float) 0;
  private boolean bIfRunScreenSaver;
  private boolean bFadeFlagOut, bFadeFlagIn = false;
  private long intervalScreenSaver = 1000;
  private long intervalKeypadeSaver = 1000;
  private long intervalFade = 100;
  private int screenWidth, screenHeight;
  /* �Cn��m���Ϥ� */
  private int intSecondsToChange = 5;
  
  /* �]�wScreen Saver�ݭn�Ψ쪺�I���� */
  private static int[] screenDrawable = new int[]
  {
    R.drawable.screen1,
    R.drawable.screen2,
    R.drawable.screen3,
    R.drawable.screen4,
    R.drawable.screen5
  };
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /* �����bsetContentView���e�I�s���ù���� */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags
    (
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    );
    setContentView(R.layout.other);
    
    /* onCreate all Widget */
    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
   
    
    /* ��l���oUserĲ�I������ɶ� */
    lastUpdateTime = new Date(System.currentTimeMillis());
    
    /* ��l��Layout�W��Widget�i���� */  }
  
 
  
  /* �ʱ�User�S���ʧ@������� */
  private Runnable mTasks01 = new Runnable() 
  {
    public void run() 
    {
      intCounter1++;
      
          /* �Ұʰ����2 */
          mHandler02.postDelayed(mTasks02, intervalScreenSaver);
          
          /* Fade Out*/
          if(intCounter1%(intSecondsToChange)==0)
          {
            bFadeFlagOut=true;
            mHandler03.postDelayed(mTasks03, intervalFade);
          }
          else
          {
            /* �bFade Out��ߧYFade In */
            if(bFadeFlagOut==true)
            {
              bFadeFlagIn=true;
              mHandler04.postDelayed(mTasks04, intervalFade);
            }
            else
            {
              bFadeFlagIn=false;
              intCounter4 = 0;
              mHandler04.removeCallbacks(mTasks04);
            }
            intCounter3 = 0;
            bFadeFlagOut = false;
          }
          bIfRunScreenSaver = true;
        }
 
      
      
  };
  
  /* Screen Saver Runnable */
  private Runnable mTasks02 = new Runnable() 
  {
    public void run() 
    {
      
        intCounter2++;
        

        showScreenSaver();
        
        //Log.i("HIPPO", "Counter2:"+Integer.toString(intCounter2));
        mHandler02.postDelayed(mTasks02, intervalScreenSaver);
     
     
    } 
  };
  
  /* Fade Out�S��Runnable */
  private Runnable mTasks03 = new Runnable() 
  {
    public void run() 
    {
     
        intCounter3++;
        
        /* �]�wImageView���z���׺��t�U�h */
        mImageView01.setAlpha(255-intCounter3*28);
        
        Log.i("HIPPO", "Fade out:"+Integer.toString(intCounter3));
        mHandler03.postDelayed(mTasks03, intervalFade);
     
    } 
  };
  
  /* Fade In�S��Runnable */
  private Runnable mTasks04 = new Runnable() 
  {
    public void run() 
    {
      
        intCounter4++;
        
        /* �]�wImageView���z���׺��G�_�� */
        mImageView01.setAlpha(intCounter4*28);
        
        mHandler04.postDelayed(mTasks04, intervalFade);
        Log.i("HIPPO", "Fade In:"+Integer.toString(intCounter4));
      
    } 
  };
  
  /* ��_�즳��Layout�i���� */
 

  
  /* �}�lScreenSaver */
  private void showScreenSaver()
  {
    /* �ù��O�@����n�����ƥ�g�b��*/
    
    if(intDrawable>4)
    {
      intDrawable = 0;
    }
    
    DisplayMetrics dm=new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    screenWidth = dm.widthPixels;
    screenHeight = dm.heightPixels;
    Bitmap bmp=BitmapFactory.decodeResource(getResources(), screenDrawable[intDrawable]);
    
    /* Matrix��� */ 
    float scaleWidth = ((float) screenWidth) / bmp.getWidth();
    float scaleHeight = ((float) screenHeight) / bmp.getHeight() ;
    
    Matrix matrix = new Matrix(); 
    /* �ϥ�Matrix.postScale�]�w����ReSize */ 
    matrix.postScale(scaleWidth, scaleHeight);
    
    /* ReSize���ɦܿù��ѪR�� */
    Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
    BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
    mImageView01.setImageDrawable(myNewBitmapDrawable);
    
    /* ��ImageView�i�� */
    mImageView01.setVisibility(View.VISIBLE);
    
    /* �C���j�]�w��Ƹm���Ϥ�ID�A��U�@��runnable2�~�|�ͮ� */
    if(intCounter2%intSecondsToChange==0)
    {
      intDrawable++;
    }
  }
  
  
  
  public void updateUserActionTime()
  {
    /* ���o���U����ƥ�ɪ��t��Time Millis */
    Date timeNow = new Date(System.currentTimeMillis());
    /* ���s�p����U����Z���W�@���R��ɶ����Z */
    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
  }
  
  public void resetScreenSaverListener()
  {
    /* �����{����Runnable */
    mHandler01.removeCallbacks(mTasks01);
    mHandler02.removeCallbacks(mTasks02);
    
    /* ���o���U����ƥ�ɪ��t��Time Millis */
    Date timeNow = new Date(System.currentTimeMillis());
    /* ���s�p����U����Z���W�@���R��ɶ����Z */
    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
    
    /* for Runnable2�A�����ù��O�@ */
    bIfRunScreenSaver = false;
    
    /* ���mRunnable1�PRunnable1��Counter */
    intCounter1 = 0;
    intCounter2 = 0;
    
 
    
    /* ���spostDelayed()�s��Runnable */
    mHandler01.postDelayed(mTasks01, intervalKeypadeSaver);
  }
  
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    // TODO Auto-generated method stub
    if(bIfRunScreenSaver==true && keyCode!=4)
    {
    
    }
    else
    {
      /* ��sUser��Ĳ�ʤ�����ɶ��W�O */
      updateUserActionTime();
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event)
  {
    // TODO Auto-generated method stub
    if(bIfRunScreenSaver==true)
    {
 
    }
    else
    {
      /* ��sUser��Ĳ�ʤ�����ɶ��W�O */
      updateUserActionTime();
    }
    return super.onTouchEvent(event);
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    mHandler01.postDelayed(mTasks01, intervalKeypadeSaver);
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    
    try
    {
      /* �������椤������� */
      mHandler01.removeCallbacks(mTasks01);
      mHandler02.removeCallbacks(mTasks02);
      mHandler03.removeCallbacks(mTasks03);
      mHandler04.removeCallbacks(mTasks04);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    super.onPause();
  }
}