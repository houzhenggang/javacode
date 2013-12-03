package irdc.ex08_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EX08_02 extends Activity 
{
  
  private Button myButton;
  private WebView mWebView1;  
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main); 
    myButton = (Button)findViewById(R.id.myButton);
    mWebView1 = (WebView) findViewById(R.id.myWebView1);
    myButton.setText("°Ù¶ÈÆû³µ");
    myButton.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        {                    
          String strURI = "http://www.baidu.com"; 
          mWebView1.loadUrl(strURI);
          Toast.makeText(
              EX08_02.this,strURI,Toast.LENGTH_LONG)
              .show();          
        }   
      }      
    });
  }
}