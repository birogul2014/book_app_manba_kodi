package com.example.books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
super.onCreate(savedInstanceState);

  setContentView(R.layout.splash);
  Thread t=new Thread()
  {

      public void run()
      {   

          try {

              sleep(4000);
              finish();
              Intent cv=new Intent(Splash.this,MainActivity.class);
              startActivity(cv);
          } 

          catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  };
  t.start();
	}
}
