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
import com.codefupanda.instamojo.model.OfferDetail;
import com.codefupanda.instamojo.model.Offers;
import com.codefupanda.instamojo.model.User;

/**
 * Get offers for the user
 * 
 * @author shashank
 */
public interface OfferService {
	
	/**
	 * Get all offers of the logged in user
	 * 
	 * @param user user object containing auth token
	 * @return all the offers of the user.
	 * @throws InstamojoException if not connected to Internet or some other network issues 
	 */
	public Offers getAllOffers(User user) throws InstamojoException;

	/**
	 * Get details of the offer
	 * 
	 * @param slug the slug of the offer whose details are required 
	 * @param user the logged in user
	 * @return details of the offer
	 * @throws InstamojoException if not connected to Internet or some other network issues 
	 */
	public OfferDetail getOfferDetail(User user, String slug) throws InstamojoException;

}
