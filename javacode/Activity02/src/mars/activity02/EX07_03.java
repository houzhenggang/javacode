package mars.activity02;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
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

public class EX07_03 extends Activity
{
	
  private TextView mTextView01;
  private ImageView mImageView01;
  private LayoutInflater mInflater01;
  private View mView01;
  private EditText mEditText01,mEditText02;
  static final private int MENU_ABOUT = Menu.FIRST;
  static final private int MENU_EXIT = Menu.FIRST+1;
private static final String ACTIVITY_TAG = null;
  private Handler mHandler01 = new Handler();
  private Handler mHandler02 = new Handler();
  private Handler mHandler03 = new Handler();
  private Handler mHandler04 = new Handler();
  private int intCounter1, intCounter2;
  private int intCounter3, intCounter4; 
  private Date lastUpdateTime;
  private long timePeriod;
  private int intDrawable;
  private float fHoldStillSecond = (float) 0;
  private boolean bIfRunScreenSaver;
  private boolean bFadeFlagOut, bFadeFlagIn = false;
  private long intervalScreenSaver = 1000;
  private long intervalKeypadeSaver = 1000;
  private long intervalFade = 100;
  private int screenWidth, screenHeight;
  PowerManager powerManager = null;    
  WakeLock wakeLock = null; 

  
  private static int[] screenDrawable = new int[]
  {
	  R.drawable.gamma000,
	  R.drawable.gamma001,
	  R.drawable.gamma002,
	  R.drawable.gamma003,
	  R.drawable.gamma004,
	  R.drawable.gamma005,
	  R.drawable.gamma006,
	  R.drawable.gamma007,
	  R.drawable.gamma008,
	  R.drawable.gamma009,
	  R.drawable.gamma010,
	  R.drawable.gamma011,
	  R.drawable.gamma012,
	  R.drawable.gamma013,
	  R.drawable.gamma014,
	  R.drawable.gamma015,
	  R.drawable.gamma016,
	  R.drawable.gamma017,
	  R.drawable.gamma018,
	  R.drawable.gamma019,
	  R.drawable.gamma020,
	  R.drawable.gamma021,
	  R.drawable.gamma022,
	  R.drawable.gamma023,
	  R.drawable.gamma024,
	  R.drawable.gamma025,
	  R.drawable.gamma026,
	  R.drawable.gamma027,
	  R.drawable.gamma028,
	  R.drawable.gamma029,
	  R.drawable.gamma030,
	  R.drawable.gamma031,
	  R.drawable.gamma032,
	  R.drawable.gamma033,
	  R.drawable.gamma034,
	  R.drawable.gamma035,
	  R.drawable.gamma036,
	  R.drawable.gamma037,
	  R.drawable.gamma038,
	  R.drawable.gamma039,
	  R.drawable.gamma040,
	  R.drawable.gamma041,
	  R.drawable.gamma042,
	  R.drawable.gamma043,
	  R.drawable.gamma044,
	  R.drawable.gamma045,
	  R.drawable.gamma046,
	  R.drawable.gamma047,
	  R.drawable.gamma048,
	  R.drawable.gamma049,
	  R.drawable.gamma050,
	  R.drawable.gamma051,
	  R.drawable.gamma052,
	  R.drawable.gamma053,
	  R.drawable.gamma054,
	  R.drawable.gamma055,
	  R.drawable.gamma056,
	  R.drawable.gamma057,
	  R.drawable.gamma058,
	  R.drawable.gamma059,
	  R.drawable.gamma060,
	  R.drawable.gamma061,
	  R.drawable.gamma062,
	  R.drawable.gamma063,
	  R.drawable.gamma064,
	  R.drawable.gamma065,
	  R.drawable.gamma066,
	  R.drawable.gamma067,
	  R.drawable.gamma068,
	  R.drawable.gamma069,
	  R.drawable.gamma070,
	  R.drawable.gamma071,
	  R.drawable.gamma072,
	  R.drawable.gamma073,
	  R.drawable.gamma074,
	  R.drawable.gamma075,
	  R.drawable.gamma076,
	  R.drawable.gamma077,
	  R.drawable.gamma078,
	  R.drawable.gamma079,
	  R.drawable.gamma080,
	  R.drawable.gamma081,
	  R.drawable.gamma082,
	  R.drawable.gamma083,
	  R.drawable.gamma084,
	  R.drawable.gamma085,
	  R.drawable.gamma086,
	  R.drawable.gamma087,
	  R.drawable.gamma088,
	  R.drawable.gamma089,
	  R.drawable.gamma090,
	  R.drawable.gamma091,
	  R.drawable.gamma092,
	  R.drawable.gamma093,
	  R.drawable.gamma094,
	  R.drawable.gamma095,
	  R.drawable.gamma096,
	  R.drawable.gamma097,
	  R.drawable.gamma098,
	  R.drawable.gamma099,
	  R.drawable.gamma100,
	  R.drawable.gamma101,
	  R.drawable.gamma102,
	  R.drawable.gamma103,
	  R.drawable.gamma104,
	  R.drawable.gamma105,
	  R.drawable.gamma106,
	  R.drawable.gamma107,
	  R.drawable.gamma108,
	  R.drawable.gamma109,
	  R.drawable.gamma110,
	  R.drawable.gamma111,
	  R.drawable.gamma112,
	  R.drawable.gamma113,
	  R.drawable.gamma114,
	  R.drawable.gamma115,
	  R.drawable.gamma116,
	  R.drawable.gamma117,
	  R.drawable.gamma118,
	  R.drawable.gamma119,
	  R.drawable.gamma120,
	  R.drawable.gamma121,
	  R.drawable.gamma122,
	  R.drawable.gamma123,
	  R.drawable.gamma124,
	  R.drawable.gamma125,
	  R.drawable.gamma126,
	  R.drawable.gamma127,
	  R.drawable.gamma128,
	  R.drawable.gamma129,
	  R.drawable.gamma130,
	  R.drawable.gamma131,
	  R.drawable.gamma132,
	  R.drawable.gamma133,
	  R.drawable.gamma134,
	  R.drawable.gamma135,
	  R.drawable.gamma136,
	  R.drawable.gamma137,
	  R.drawable.gamma138,
	  R.drawable.gamma139,
	  R.drawable.gamma140,
	  R.drawable.gamma141,
	  R.drawable.gamma142,
	  R.drawable.gamma143,
	  R.drawable.gamma144,
	  R.drawable.gamma145,
	  R.drawable.gamma146,
	  R.drawable.gamma147,
	  R.drawable.gamma148,
	  R.drawable.gamma149,
	  R.drawable.gamma150,
	  R.drawable.gamma151,
	  R.drawable.gamma152,
	  R.drawable.gamma153,
	  R.drawable.gamma154,
	  R.drawable.gamma155,
	  R.drawable.gamma156,
	  R.drawable.gamma157,
	  R.drawable.gamma158,
	  R.drawable.gamma159,
	  R.drawable.gamma160,
	  R.drawable.gamma161,
	  R.drawable.gamma162,
	  R.drawable.gamma163,
	  R.drawable.gamma164,
	  R.drawable.gamma165,
	  R.drawable.gamma166,
	  R.drawable.gamma167,
	  R.drawable.gamma168,
	  R.drawable.gamma169,
	  R.drawable.gamma170,
	  R.drawable.gamma171,
	  R.drawable.gamma172,
	  R.drawable.gamma173,
	  R.drawable.gamma174,
	  R.drawable.gamma175,
	  R.drawable.gamma176,
	  R.drawable.gamma177,
	  R.drawable.gamma178,
	  R.drawable.gamma179,
	  R.drawable.gamma180,
	  R.drawable.gamma181,
	  R.drawable.gamma182,
	  R.drawable.gamma183,
	  R.drawable.gamma184,
	  R.drawable.gamma185,
	  R.drawable.gamma186,
	  R.drawable.gamma187,
	  R.drawable.gamma188,
	  R.drawable.gamma189,
	  R.drawable.gamma190,
	  R.drawable.gamma191,
	  R.drawable.gamma192,
	  R.drawable.gamma193,
	  R.drawable.gamma194,
	  R.drawable.gamma195,
	  R.drawable.gamma196,
	  R.drawable.gamma197,
	  R.drawable.gamma198,
	  R.drawable.gamma199,
	  R.drawable.gamma200,
	  R.drawable.gamma201,
	  R.drawable.gamma202,
	  R.drawable.gamma203,
	  R.drawable.gamma204,
	  R.drawable.gamma205,
	  R.drawable.gamma206,
	  R.drawable.gamma207,
	  R.drawable.gamma208,
	  R.drawable.gamma209,
	  R.drawable.gamma210,
	  R.drawable.gamma211,
	  R.drawable.gamma212,
	  R.drawable.gamma213,
	  R.drawable.gamma214,
	  R.drawable.gamma215,
	  R.drawable.gamma216,
	  R.drawable.gamma217,
	  R.drawable.gamma218,
	  R.drawable.gamma219,
	  R.drawable.gamma220,
	  R.drawable.gamma221,
	  R.drawable.gamma222,
	  R.drawable.gamma223,
	  R.drawable.gamma224,
	  R.drawable.gamma225,
	  R.drawable.gamma226,
	  R.drawable.gamma227,
	  R.drawable.gamma228,
	  R.drawable.gamma229,
	  R.drawable.gamma230,
	  R.drawable.gamma231,
	  R.drawable.gamma232,
	  R.drawable.gamma233,
	  R.drawable.gamma234,
	  R.drawable.gamma235,
	  R.drawable.gamma236,
	  R.drawable.gamma237,
	  R.drawable.gamma238,
	  R.drawable.gamma239,
	  R.drawable.gamma240,
	  R.drawable.gamma241,
	  R.drawable.gamma242,
	  R.drawable.gamma243,
	  R.drawable.gamma244,
	  R.drawable.gamma245,
	  R.drawable.gamma246,
	  R.drawable.gamma247,
	  R.drawable.gamma248,
	  R.drawable.gamma249,
	  R.drawable.gamma250,
	  R.drawable.gamma251,
	  R.drawable.gamma252,
	  R.drawable.gamma253,
	  R.drawable.gamma254,
	  R.drawable.gamma255,

   
  };
  @SuppressWarnings("unused")

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    String factortwoStr = intent.getStringExtra("two");
	int a =Integer.parseInt(factortwoStr);
	intDrawable = a-1;
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags
    (
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    );
    setContentView(R.layout.other);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
    lastUpdateTime = new Date(System.currentTimeMillis());
    }
  
 
  

  private Runnable mTasks01 = new Runnable() 
  {
    public void run() 
    {
      intCounter1++;
          mHandler02.postDelayed(mTasks02, intervalScreenSaver);
          Intent intent = getIntent();
  		String factorOneStr = intent.getStringExtra("one");
  		int intSecondsToChange = Integer.parseInt(factorOneStr);
          if(intCounter1%(intSecondsToChange)==0)
          {
            bFadeFlagOut=true;
            mHandler03.postDelayed(mTasks03, intervalFade);
          }
          else
          {
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

  private Runnable mTasks02 = new Runnable() 
  {
    public void run() 
    {
      
        intCounter2++;
        showScreenSaver();
        mHandler02.postDelayed(mTasks02, intervalScreenSaver);
     
     
    } 
  };
  

  private Runnable mTasks03 = new Runnable() 
  {
    public void run() 
    {
     
        intCounter3++;      
        mImageView01.setAlpha(255-intCounter3*28);
        Log.i("HIPPO", "Fade out:"+Integer.toString(intCounter3));
        mHandler03.postDelayed(mTasks03, intervalFade);
     
    } 
  };
  
  private Runnable mTasks04 = new Runnable() 
  {
    public void run() 
    {
      
        intCounter4++;
        mImageView01.setAlpha(intCounter4*28);      
        mHandler04.postDelayed(mTasks04, intervalFade);
        Log.i("HIPPO", "Fade In:"+Integer.toString(intCounter4));
      
    } 
  };

  private void showScreenSaver()
  {   
	  Intent intent = getIntent();
	  String factorOneStr = intent.getStringExtra("one");
	  int intSecondsToChange = Integer.parseInt(factorOneStr);
    	if(intDrawable> 255)
    	{
    		intDrawable = 0;
    	}
		Log.v(EX07_03.ACTIVITY_TAG, "xuejh-------1"+ intSecondsToChange);
		Log.v(EX07_03.ACTIVITY_TAG, "xuejh-------1"+ intDrawable);
        
    
    DisplayMetrics dm=new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    screenWidth = dm.widthPixels;
    screenHeight = dm.heightPixels;
    Bitmap bmp=BitmapFactory.decodeResource(getResources(), screenDrawable[intDrawable]);
    float scaleWidth = ((float) screenWidth) / bmp.getWidth();
    float scaleHeight = ((float) screenHeight) / bmp.getHeight() ;
    
    Matrix matrix = new Matrix(); 
    matrix.postScale(scaleWidth, scaleHeight);
    Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
    BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
    mImageView01.setImageDrawable(myNewBitmapDrawable);
    mImageView01.setVisibility(View.VISIBLE);
    if(intCounter2%intSecondsToChange==0)
    {
      intDrawable++;
    }
  }
  
  
  
  public void updateUserActionTime()
  {
    Date timeNow = new Date(System.currentTimeMillis());
    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
  }
  
  public void resetScreenSaverListener()
  {
    mHandler01.removeCallbacks(mTasks01);
    mHandler02.removeCallbacks(mTasks02);
    Date timeNow = new Date(System.currentTimeMillis());

    timePeriod = (long)timeNow.getTime() - (long)lastUpdateTime.getTime();
    lastUpdateTime.setTime(timeNow.getTime());
    bIfRunScreenSaver = false;
    intCounter1 = 0;
    intCounter2 = 0;
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
  