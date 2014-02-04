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
package com.codefupanda.instamojo.service.impl;

import static com.codefupanda.instamojo.constant.Constants.LOGIN_REST_URL;
import static com.codefupanda.instamojo.constant.Constants.PASSWORD;
import static com.codefupanda.instamojo.constant.Constants.USERNAME;
import static com.codefupanda.instamojo.constant.Constants.X_APP_ID;
import static com.codefupanda.instamojo.constant.Constants.X_APP_ID_VALUE;

import java.io.IOException;

import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.exception.InstamojoRestException;
import com.codefupanda.instamojo.model.User;
import com.codefupanda.instamojo.service.LoginService;
import com.codefupanda.instamojo.service.rest.RestCallBuilder;
import com.codefupanda.instamojo.service.util.JsonUtil;

/**
 * Provides functions for logging in and 
 * to check if user is already logged in.
 * 
 * @author shashank
 */
public class LoginServiceImpl implements LoginService {
	
	/**
	 * Check is user is logged in.
	 * We will check if we have a auth token with us.
	 */
	@Override
	public boolean isLoggedIn(User user) {
		return false;
	}
	
	/**
	 * log in with username/emailId and password
	 * 
	 * @param userName
	 * @param password
	 * @throws InstamojoRestException 
	 */
	@Override
	public void login(User user, String password) throws InstamojoException {
		RestCallBuilder builder = new RestCallBuilder(LOGIN_REST_URL);
		builder.addHeader(X_APP_ID, X_APP_ID_VALUE);
		builder.addParams(USERNAME, user.getEmailId());
		builder.addParams(PASSWORD, password);
		
		try {
			builder.doPost();
			User tempUser = JsonUtil.toObject(builder.getResponse(), User.class);
			user.setToken(tempUser.getToken());
			user.setSuccess(tempUser.getSuccess());
			user.setMessage(tempUser.getMessage());
		} catch (IOException e) {
			throw new InstamojoException();
		}
	}
}
