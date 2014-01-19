/*
 * Copyright (C) Shashank Kulkarni - Shashank.physics AT gmail DOT com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.codefupanda.instamojo.activity;

import static com.codefupanda.instamojo.constant.Constants.WELCOME_SCREEN_LENGTH;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codefupanda.instamojo.R;
import com.codefupanda.instamojo.listener.LoginListener;

/**
 * Main Activity. Program starts from here!
 * 
 * @author shashank
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Button button = (Button) findViewById(R.id.signin);
				EditText email = (EditText) findViewById(R.id.email);
				EditText password = (EditText) findViewById(R.id.passpord);
				
				button.setVisibility(View.VISIBLE);
				email.setVisibility(View.VISIBLE);
				password.setVisibility(View.VISIBLE);
			}
		}, WELCOME_SCREEN_LENGTH);
        
        Button singInButton = (Button) findViewById(R.id.signin);
        singInButton.setOnClickListener(new LoginListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
