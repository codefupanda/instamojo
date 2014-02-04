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
package com.codefupanda.instamojo.listener;

import static com.codefupanda.instamojo.constant.Constants.NO_INTERNET;
import static com.codefupanda.instamojo.constant.Constants.SIGN_IN_FAIL;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codefupanda.instamojo.R;
import com.codefupanda.instamojo.activity.HomeActivity;
import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.model.Offers;
import com.codefupanda.instamojo.model.User;
import com.codefupanda.instamojo.service.LoginService;
import com.codefupanda.instamojo.service.OfferService;
import com.codefupanda.instamojo.service.ServiceFactory;
import com.codefupanda.instamojo.service.util.AndroidUtil;

/**
 * Sign in button listener.
 * 
 * @author shashank
 */
public class LoginListener implements OnClickListener {

	private LoginService loginService;
	private OfferService offerService;
	/**
	 * Constructor
	 */
	public LoginListener() {
		loginService = ServiceFactory.loginService();
		offerService = ServiceFactory.offerService();
	}

	@Override
	public void onClick(final View view) {
		final User user = new User();
		Activity host = (Activity) view.getContext();
		
		// set the progress bar
		final ProgressBar bar = (ProgressBar) host.findViewById(R.id.loginProgressBar);
		bar.setVisibility(View.VISIBLE);
		
		// take values from fields and make them invisible
		final TextView emailId = (TextView) host.findViewById(R.id.email);
		emailId.setVisibility(View.GONE);
		user.setEmailId(emailId.getText().toString());
		
		final TextView passwordR = (TextView) host.findViewById(R.id.passpord);
		passwordR.setVisibility(View.GONE);
		final String password = passwordR.getText().toString();

		InputMethodManager imm = (InputMethodManager)host.getSystemService(Service.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(passwordR.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(emailId.getWindowToken(), 0);
		
		final Button loginButton = (Button) host.findViewById(R.id.signin);
		loginButton.setVisibility(View.GONE);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					loginService.login(user, password);
					if (user.getSuccess()) {
						// if login successful, get all the offers
						Offers offers = offerService.getAllOffers(user);
						
						Intent intent = new Intent(view.getContext(), HomeActivity.class);
						intent.putExtra("user", user);
						intent.putExtra("offers", offers);
						view.getContext().startActivity(intent);
					} else {
						// if login is unsuccessful, remove progress-bar, show input fields and pop-up message
						makeVisible(bar, emailId, passwordR, loginButton);
						
						AndroidUtil.showAlert(view.getContext(), SIGN_IN_FAIL, user.getMessage());
					}
				} catch (InstamojoException e) {
					// log error!
					makeVisible(bar, emailId, passwordR, loginButton);
					AndroidUtil.showAlert(view.getContext(), SIGN_IN_FAIL, NO_INTERNET);
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private void makeVisible(ProgressBar bar, TextView emailId,
			TextView passwordR, Button loginButton) {
		bar.setVisibility(View.GONE);
		emailId.setVisibility(View.VISIBLE);
		passwordR.setVisibility(View.VISIBLE);
		loginButton.setVisibility(View.VISIBLE);
	}
}
