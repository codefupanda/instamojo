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
package com.codefupanda.instamojo.service;

import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.exception.InstamojoRestException;
import com.codefupanda.instamojo.model.User;

/**
 * Provides functions for logging in and 
 * to check if user is already logged in.
 * 
 * @author shashank
 */
public interface LoginService {
	
	/**
	 * Check is user is logged in.
	 * We will check if we have a auth token with us.
	 */
	public boolean isLoggedIn(User user);
	
	/**
	 * log in with username/emailId and password
	 * 
	 * @param userName
	 * @param password
	 * @throws InstamojoRestException 
	 */
	public void login(User user, String password) throws InstamojoException;

}
