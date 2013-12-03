package irdc.ex10_05;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EX10_05 extends Activity implements SurfaceHolder.Callback
{
  /* �إߨp��Camera���� */
  private Camera mCamera01;
  private Button mButton01, mButton02, mButton03;
  
  /* �@��review�ӤU�Ӫ��ۤ�� */
  private ImageView mImageView01;
  private String TAG = "HIPPO";
  private SurfaceView mSurfaceView01;
  private SurfaceHolder mSurfaceHolder01;
  
  /* �w�]�۾��w��Ҧ���false */
  private boolean bIfPreview = false;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /* �����ε{�����ù�����A���ϥ�title bar */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    
    setContentView(R.layout.main);
    DrawCaptureRect mDraw = new DrawCaptureRect
    (
      EX10_05.this,
      // PORTRAIT
      //110, 10, 100, 100,
      190, 10, 100, 100,
      //181, 1, 118, 118,
      getResources().getColor(R.drawable.lightred)
    );
    addContentView(mDraw, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    
    /* ��o�ù��ѪR���� */
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    
    mImageView01 = (ImageView) findViewById(R.id.myImageView1);
    
    /* �HSurfaceView�@���۾�Preview���� */
    mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
    
    /* ô��SurfaceView�A��oSurfaceHolder���� */
    mSurfaceHolder01 = mSurfaceView01.getHolder();
    
    /* Activity������@SurfaceHolder.Callback */
    mSurfaceHolder01.addCallback(EX10_05.this);
    
    /* �B�~���]�w�w��j�p�]�w�A�b�����ϥ� */
    //mSurfaceHolder01.setFixedSize(160, 120);
      
    /*
     * �HSURFACE_TYPE_PUSH_BUFFERS(3)
     * �@��SurfaceHolder��ܫ��A 
     * */
    mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton02 = (Button)findViewById(R.id.myButton2);
    mButton03 = (Button)findViewById(R.id.myButton3);
    
    /* �}�Ҭ۾���Preview */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* �ۭq��l�ƶ}�Ҭ۾���� */
        try
        {
          initCamera();
        } catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    
    /* ����Preview�ά۾� */
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* �ۭq���m�۾��A�������۾��w���� */
        resetCamera();
      }
    });
    
    /* ��� */
    mButton03.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        /* �ۭq��Ө�� */
        takePicture();
      }
    });
  }
  
  /* �ۭq��l�۾���� */
  private void initCamera() throws IOException
  {
    if(!bIfPreview)
    {
      /* �Y�۾��D�b�w��Ҧ��A�h�}�Ҭ۾� */
      mCamera01 = Camera.open();
    }
    
    if (mCamera01 != null && !bIfPreview)
    {
      Log.i(TAG, "inside the camera");
      
      /* �إ�Camera.Parameters���� */
      Camera.Parameters parameters = mCamera01.getParameters();
      
      /* �]�w�ۤ�榡��JPEG */
      parameters.setPictureFormat(PixelFormat.JPEG);
      //parameters.setPictureFormat(PixelFormat);
      
      /* ��wpreview���ù��j�p */
      parameters.setPreviewSize(160, 120);
      
      /* �]�w�Ϥ�ѪR�פj�p */
      parameters.setPictureSize(160, 120);
      
      /* ��� */
      //parameters.set("autofocus", "true");
      //parameters.set("quality", "200");
      
      /* �NCamera.Parameters�]�w��Camera */
      mCamera01.setParameters(parameters);
      
      //String thing = mCamera01.getParameters().flatten(); 
      //System.out.println(thing);
      
      /* setPreviewDisplay�ߤ@���ѼƬ�SurfaceHolder */
      mCamera01.setPreviewDisplay(mSurfaceHolder01);
      
      /* �ߧY����Preview */
      try
      {
        mCamera01.startPreview();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
      bIfPreview = true;
    }
  }
  
  /* ����^��v�� */ 
  private void takePicture() 
  {
    if (mCamera01 != null && bIfPreview) 
    {
      /* �I�stakePicture()��k��� */
      mCamera01.takePicture(shutterCallback, rawCallback, jpegCallback);
    }
  }
  
  /* �۾����m */
  private void resetCamera()
  {
    if (mCamera01 != null && bIfPreview)
    {
      try
      {
        mCamera01.stopPreview();
        /* ����ǲߡA����Camera���� */
        //mCamera01.release();
        mCamera01 = null;
        bIfPreview = false;
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
   
  private ShutterCallback shutterCallback = new ShutterCallback() 
  { 
    public void onShutter() 
    { 
      // Shutter has closed 
    } 
  }; 
   
  private PictureCallback rawCallback = new PictureCallback() 
  { 
    public void onPictureTaken(byte[] _data, Camera _camera) 
    { 
      // TODO Handle RAW image data 
    } 
  }; 

  private PictureCallback jpegCallback = new PictureCallback() 
  {
    public void onPictureTaken(byte[] _data, Camera _camera)
    {
      // TODO Handle JPEG image data
      
      try
      {
        /* ����ǲߡA�ѽX���� */
        /*
        import java.io.File;
        
        String strQRTestFile = "/sdcard/test_qrcode.jpg"; 
        File myImageFile = new File(strQRTestFile);
        
        if(myImageFile.exists())
        {
          Bitmap myBmp = BitmapFactory.decodeFile(strQRTestFile); 
          mImageView01.setImageBitmap(myBmp);
          String strQR2 = decodeQRImage(myBmp);
          if(strQR2!="")
          {
            if (URLUtil.isNetworkUrl(strQR2))
            {
              mMakeTextToast(strQR2, true);
              Uri mUri = Uri.parse(strQR2);
              Intent intent = new Intent(Intent.ACTION_VIEW, mUri);
              startActivity(intent);
            }
            else
            {
              mMakeTextToast(strQR2, true);
            }
          }
        }
        */
        
        /* onPictureTaken�ǤJ���Ĥ@�ӰѼƧY���ۤ�byte */
        Bitmap bm = null;
        bm = BitmapFactory.decodeByteArray(_data, 0, _data.length);
        
        int resizeWidth = 160;
        int resizeHeight = 120;
        float scaleWidth = ((float) resizeWidth) / bm.getWidth();
        float scaleHeight = ((float) resizeHeight) / bm.getHeight();
        
        Matrix matrix = new Matrix();
        /* �ϥ�Matrix.postScale��k�Y�p Bitmap Size*/
        matrix.postScale(scaleWidth, scaleHeight);
        
        /* �إ߷s��Bitmap���� */
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        
        /* �^��4:3�����ɪ��m������س���100x100���� */
        Bitmap resizedBitmapSquare = Bitmap.createBitmap(resizedBitmap, 30, 10, 100, 100);
        //Bitmap resizedBitmapSquare = Bitmap.createBitmap(resizedBitmap, 21, 1, 118, 118);
        //Bitmap resizedBitmapSquare = Bitmap.createBitmap(resizedBitmap, 60, 20, 200, 200);
        
        /* �N��Ӫ����ɥHImageView��ܥX�� */
        mImageView01.setImageBitmap(resizedBitmapSquare);
        
        /* �N�ǤJ�����ɸѽX���r�� */
        String strQR2 = decodeQRImage(resizedBitmapSquare);
        if(strQR2!="")
        {
          if (URLUtil.isNetworkUrl(strQR2))
          {
            /* OMIA�W�d�A���}��X�A�}���s��W�� */
            mMakeTextToast(strQR2, true);
            Uri mUri = Uri.parse(strQR2);
            Intent intent = new Intent(Intent.ACTION_VIEW, mUri);
            startActivity(intent);
          }
          else if(eregi("wtai://",strQR2))
          {
            /* OMIA�W�d�A��������q�ܮ榡 */
            String[] aryTemp01 = strQR2.split("wtai://");
            Intent myIntentDial = new Intent("android.intent.action.CALL",Uri.parse("tel:"+aryTemp01[1]));
            startActivity(myIntentDial); 
          }
          else if(eregi("TEL:",strQR2))
          {
            /* OMIA�W�d�A��������q�ܮ榡 */
            String[] aryTemp01 = strQR2.split("TEL:");
            Intent myIntentDial = new Intent("android.intent.action.CALL",Uri.parse("tel:"+aryTemp01[1]));
            startActivity(myIntentDial);
          }
          else
          {
            /* �Y�ȬO��r�A�h�HToast��ܥX�� */
            mMakeTextToast(strQR2, true);
          }
        }
        
        /* ��ܧ����ɡA�ߧY���m�۾��A�������w�� */
        resetCamera();
        
        /* �A���s�Ұʬ۾��~��w�� */
        initCamera();
      }
      catch (Exception e)
      {
        Log.e(TAG, e.getMessage());
      }
    }
  };
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX10_05.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX10_05.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  /* �ѽX�ǤJ��Bitmap���� */
  public String decodeQRImage(Bitmap myBmp)
  {
    String strDecodedData = "";
    try
    {
      QRCodeDecoder decoder = new QRCodeDecoder();
      strDecodedData  = new String(decoder.decode(new AndroidQRCodeImage(myBmp)));
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return strDecodedData; 
  }
  
  /* �ۭq��@QRCodeImage���O */
  class AndroidQRCodeImage implements QRCodeImage
  {
    Bitmap image;
    
    public AndroidQRCodeImage(Bitmap image)
    {
      this.image = image;
    }
    
    public int getWidth()
    {
      return image.getWidth();
    }
    
    public int getHeight()
    {
      return image.getHeight();
    }
    
    public int getPixel(int x, int y)
    {
      return image.getPixel(x, y);
    }   
  }
  
  /* ø�s�۾��w��e���̪�����Τ�� */
  class DrawCaptureRect extends View
  {
    private int colorFill;
    private int intLeft,intTop, intWidth,intHeight;
    
    public DrawCaptureRect(Context context, int intX, int intY, int intWidth, int intHeight, int colorFill)
    {
      super(context);
      this.colorFill = colorFill;
      this.intLeft = intX;
      this.intTop = intY;
      this.intWidth = intWidth;
      this.intHeight = intHeight;
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
      Paint mPaint01 = new Paint();
      mPaint01.setStyle(Paint.Style.FILL);
      mPaint01.setColor(colorFill);
      mPaint01.setStrokeWidth(1.0F);
      /* �b�e���Wø�s���⪺�|���ؽu */
      canvas.drawLine(this.intLeft, this.intTop, this.intLeft+intWidth, this.intTop, mPaint01);
      canvas.drawLine(this.intLeft, this.intTop, this.intLeft, this.intTop+intHeight, mPaint01);
      canvas.drawLine(this.intLeft+intWidth, this.intTop, this.intLeft+intWidth, this.intTop+intHeight, mPaint01);
      canvas.drawLine(this.intLeft, this.intTop+intHeight, this.intLeft+intWidth, this.intTop+intHeight, mPaint01);
      super.onDraw(canvas);
    }
  }
  
  /* �ۭq���r���� */
  public static boolean eregi(String strPat, String strUnknow)
  {
    String strPattern = "(?i)"+strPat;
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strUnknow);
    return m.find();
  }
  
  /* �ۭq�r���N��� */
  public String eregi_replace(String strFrom, String strTo, String strTarget)
  {
    String strPattern = "(?i)"+strFrom;
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strTarget);
    if(m.find())
    {
      return strTarget.replaceAll(strFrom, strTo);
    }
    else
    {
      return strTarget;
    }
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w, int h)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceCreated(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Destroyed");
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
  }
}