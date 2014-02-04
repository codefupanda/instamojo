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

import static com.codefupanda.instamojo.constant.Constants.GET_OFFER_DETAILS_REST_URL;
import static com.codefupanda.instamojo.constant.Constants.GET_USER_OFFERS_REST_URL;
import static com.codefupanda.instamojo.constant.Constants.X_APP_ID;
import static com.codefupanda.instamojo.constant.Constants.X_APP_ID_VALUE;
import static com.codefupanda.instamojo.constant.Constants.X_AUTH_TOKEN;

import java.io.IOException;

import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.model.OfferDetail;
import com.codefupanda.instamojo.model.Offers;
import com.codefupanda.instamojo.model.User;
import com.codefupanda.instamojo.service.OfferService;
import com.codefupanda.instamojo.service.rest.RestCallBuilder;
import com.codefupanda.instamojo.service.util.JsonUtil;

/**
 * Get offers for the user
 * 
 * @author shashank
 */
public class OfferServiceImpl implements OfferService {
	
	/**
	 * Get all offers of the logged in user
	 * 
	 * @param user user object containing auth token
	 * @return all the offers of the user.
	 * @throws InstamojoException if not connected to Internet or some other network issues 
	 */
	@Override
	public Offers getAllOffers(User user) throws InstamojoException {
		RestCallBuilder builder = new RestCallBuilder(GET_USER_OFFERS_REST_URL);
		builder.addHeader(X_APP_ID, X_APP_ID_VALUE);
		builder.addHeader(X_AUTH_TOKEN, user.getToken());
		
		try {
			builder.doGet();
			Offers offers = JsonUtil.toObject(builder.getResponse(), Offers.class);
			return offers;
		} catch (IOException e) {
			// log error!
			throw new InstamojoException();
		}
	}

	/**
	 * Get details of the offer
	 * 
	 * @param slug the slug of the offer whose details are required 
	 * @param user the logged in user
	 * @return details of the offer
	 * @throws InstamojoException if not connected to Internet or some other network issues 
	 */
	@Override
	public OfferDetail getOfferDetail(User user, String slug) throws InstamojoException {
		RestCallBuilder builder = new RestCallBuilder(String.format(GET_OFFER_DETAILS_REST_URL, slug));
		builder.addHeader(X_APP_ID, X_APP_ID_VALUE);
		builder.addHeader(X_AUTH_TOKEN, user.getToken());
		
		try {
			builder.doGet();
			return JsonUtil.toObject(builder.getResponse(), OfferDetail.class);
		} catch (IOException e) {
			// log error!
			throw new InstamojoException();
		}
	}
}
