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

import java.util.ArrayList;
import java.util.List;

import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.model.Offer;
import com.codefupanda.instamojo.model.User;

/**
 * Get offers for the user
 * 
 * @author shashank
 */
public class OfferService {
	
	/**
	 * 
	 * @param user user object containing auth token
	 * @return all the offers of the user. <code>null</code> if results could not be retrieved
	 * @throws InstamojoException
	 */
	public List<Offer> getAllOffers(User user) throws InstamojoException {
//		RestCallBuilder builder = new RestCallBuilder(GET_USER_OFFERS_REST_URL);
//		builder.addHeader(X_APP_ID, X_APP_ID_VALUE);
//		builder.addHeader(X_AUTH_TOKEN, user.getToken());
//		
//		try {
//			builder.doGet();
//			Offers offers = JsonUtil.toObject(builder.getResponse(), Offers.class);
//			if(offers.getSuccess()) {
//				return offers.getOffers();
//			}
//		} catch (IOException e) {
//			// log error!
//			throw new InstamojoException();
//		}
//		return null;
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(new Offer("test1"));
		offers.add(new Offer("test2"));
		offers.add(new Offer("test3"));
		return offers;
	}
}
