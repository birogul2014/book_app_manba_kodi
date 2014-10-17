package com.example.books;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

public class MainActivity extends Activity {

	 final Activity activity = this;
		public WebView webView;
		private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
  super.onCreate(savedInstanceState);
  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
  setContentView(R.layout.activity_main);
  Parse.initialize(this, "AlAOHBjWatZhMBmoeqdkMsADhbtzVLTH1o1O4hXL", "HTh4QDkCKISpSzcnO11tpYsU6MzCWXC2vV6HFJvR");
	ParseAnalytics.trackAppOpened(getIntent());
	  ParseUser.enableAutomaticUser();
	   PushService.setDefaultPushCallback(this, MainActivity.class);
      ParseInstallation.getCurrentInstallation().saveInBackground();
      ParseACL defaultACL = new ParseACL();
  webView = (WebView) findViewById(R.id.webView1);
  webView.getSettings().setJavaScriptEnabled(true);

  pd = new ProgressDialog(MainActivity.this);
  pd.setMessage("يۈكلىنىۋاتىدۇ...");
  pd.show();

  webView.setWebViewClient(new WebViewClient() {
  
      @Override
      public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {


          // API level 5: WebViewClient.ERROR_HOST_LOOKUP
          if (errorCode == -2) {
            String summary = "<html><body style='background: white;'><center><p style='color: black;text-align:right;'>ھەي قىرىندىشىم ئاۋال تورغا ئۇلاڭ،ئاندىن ئەپنى نورمال ئىشلىتەلەيسىز</p></center></body></html>";    
            view.loadData(summary, "text/html; charset=utf-8",null);
            return;
          }

          // Default behaviour
          super.onReceivedError(view, errorCode, description, failingUrl);
        }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url)
      {
    	  if (!pd.isShowing()) {
    	        pd.show();
    	    }
          if(Uri.parse(url).getHost().endsWith("ttormahiri.github.io")) {
              return false;
          }
           
          Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
          view.getContext().startActivity(intent);
          return true;
      }
      @Override
      public void onPageFinished(WebView view, String url) {
          System.out.println("يۈكلىنىپ بولدى");
          if (pd.isShowing()) {
              pd.dismiss();
          }
      }
  });
   
  webView.loadUrl("http://ttormahiri.github.io/book/index.html");
		
	
		

	}
}
