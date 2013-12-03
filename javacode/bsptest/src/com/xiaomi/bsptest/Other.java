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
  
  /* LayoutInflater物件作為新建AlertDialog之用 */
  private LayoutInflater mInflater01;
  
  /* 輸入解鎖的View */
  private View mView01;
  private EditText mEditText01,mEditText02;
  
  /* menu選項identifier，用以識別事件 */
  static final private int MENU_ABOUT = Menu.FIRST;
  static final private int MENU_EXIT = Menu.FIRST+1;
  private Handler mHandler01 = new Handler();
  private Handler mHandler02 = new Handler();
  private Handler mHandler03 = new Handler();
  private Handler mHandler04 = new Handler();
  /* 控制User靜止與否的Counter */
  private int intCounter1, intCounter2;
  /* 控制FadeIn與Fade Out的Counter */
  private int intCounter3, intCounter4;
  /* 控制循序替換背景圖ID的Counter  */
  private int intDrawable=0;
  /* 上一次User有動作的Time Stamp */
  private Date lastUpdateTime;
  /* 計算User共幾秒沒有動作 */
  private long timePeriod;
  /* 靜止超過n秒將自動進入螢幕保護 */
  private float fHoldStillSecond = (float) 0;
  private boolean bIfRunScreenSaver;
  private boolean bFadeFlagOut, bFadeFlagIn = false;
  private long intervalScreenSaver = 1000;
  private long intervalKeypadeSaver = 1000;
  private long intervalFade = 100;
  private int screenWidth, screenHeight;
  /* 每n秒置換圖片 */
  private int intSecondsToChange = 5;
  
  /* 設定Screen Saver需要用到的背景圖 */
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
    
    /* 必須在setContentView之前呼叫全螢幕顯示 */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags
    (
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    );
    setContentView(R.layout.other);
    
    /* onCreate all Widget */
    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
   
    
    /* 初始取得User觸碰手機的時間 */
    lastUpdateTime = new Date(System.currentTimeMillis());
    
    /* 初始化Layout上的Widget可見性 */  }
  
 
  
  /* 監控User沒有動作的執行緒 */
  private Runnable mTasks01 = new Runnable() 
  {
    public void run() 
    {
      intCounter1++;
      
          /* 啟動執行緒2 */
          mHandler02.postDelayed(mTasks02, intervalScreenSaver);
          
          /* Fade Out*/
          if(intCounter1%(intSecondsToChange)==0)
          {
            bFadeFlagOut=true;
            mHandler03.postDelayed(mTasks03, intervalFade);
          }
          else
          {
            /* 在Fade Out後立即Fade In */
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
  
  /* Fade Out特效Runnable */
  private Runnable mTasks03 = new Runnable() 
  {
    public void run() 
    {
     
        intCounter3++;
        
        /* 設定ImageView的透明度漸暗下去 */
        mImageView01.setAlpha(255-intCounter3*28);
        
        Log.i("HIPPO", "Fade out:"+Integer.toString(intCounter3));
        mHandler03.postDelayed(mTasks03, intervalFade);
     
    } 
  };
  
  /* Fade In特效Runnable */
  private Runnable mTasks04 = new Runnable() 
  {
    public void run() 
    {
      
        intCounter4++;
        
        /* 設定ImageView的透明度漸亮起來 */
        mImageView01.setAlpha(intCounter4*28);
        
        mHandler04.postDelayed(mTasks04, intervalFade);
        Log.i("HIPPO", "Fade In:"+Integer.toString(intCounter4));
      
    } 
  };
  
  /* 恢復原有的Layout可視性 */
 

  
  /* 開始ScreenSaver */
  private void showScreenSaver()
  {
    /* 螢幕保護之後要做的事件寫在此*/
    
    if(intDrawable>4)
    {
      intDrawable = 0;
    }
    
    DisplayMetrics dm=new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    screenWidth = dm.widthPixels;
    screenHeight = dm.heightPixels;
    Bitmap bmp=BitmapFactory.decodeResource(getResources(), screenDrawable[intDrawable]);
    
    /* Matrix比例 */ 
    float scaleWidth = ((float) screenWidth) / bmp.getWidth();
    float scaleHeight = ((float) screenHeight) / bmp.getHeight() ;
    
    Matrix matrix = new Matrix(); 
    /* 使用Matrix.postScale設定維度ReSize */ 
    matrix.postScale(scaleWidth, scaleHeight);
    
    /* ReSize圖檔至螢幕解析度 */
    Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
    BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
    mImageView01.setImageDrawable(myNewBitmapDrawable);
    
    /* 使ImageView可見 */
    mImageView01.setVisibility(View.VISIBLE);
    
    /* 每間隔設定秒數置換圖片ID，於下一個runnable2才會生效 */
    if(intCounter2%intSecondsToChange==0)
    {
      intDrawable++;
    }
  }
  
  
  
  public void updateUserActionTime()
  {
    /* 取得按下按鍵事件時的系統Time Millis */
    Date timeNow = new Date(System.currentTimeMillis());
    /* 重新計算按下按鍵距離上一次靜止的時間間距 */
    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
  }
  
  public void resetScreenSaverListener()
  {
    /* 移除現有的Runnable */
    mHandler01.removeCallbacks(mTasks01);
    mHandler02.removeCallbacks(mTasks02);
    
    /* 取得按下按鍵事件時的系統Time Millis */
    Date timeNow = new Date(System.currentTimeMillis());
    /* 重新計算按下按鍵距離上一次靜止的時間間距 */
    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
    
    /* for Runnable2，取消螢幕保護 */
    bIfRunScreenSaver = false;
    
    /* 重置Runnable1與Runnable1的Counter */
    intCounter1 = 0;
    intCounter2 = 0;
    
 
    
    /* 重新postDelayed()新的Runnable */
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
      /* 更新User未觸動手機的時間戳記 */
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
      /* 更新User未觸動手機的時間戳記 */
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
      /* 移除執行中的執行緒 */
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