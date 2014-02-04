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

import static com.codefupanda.instamojo.constant.Constants.FAILED;
import static com.codefupanda.instamojo.constant.Constants.NO_INTERNET;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codefupanda.instamojo.R;
import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.model.Offer;
import com.codefupanda.instamojo.model.OfferDetail;
import com.codefupanda.instamojo.model.Offers;
import com.codefupanda.instamojo.model.User;
import com.codefupanda.instamojo.service.ServiceFactory;
import com.codefupanda.instamojo.service.util.AndroidUtil;

/**
 * Activity for Home screen
 * 
 * @author shashank
 */
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Serializable user = this.getIntent().getExtras()
				.getSerializable("user");

		if (user == null) {
			Intent intent = new Intent(this, MainActivity.class);
			this.startActivity(intent);
			return;
		}
		final Serializable offers = this.getIntent().getExtras()
				.getSerializable("offers");
		displayOffers(user, offers);
	}


	private void displayOffers(final Serializable user, final Serializable offers) {
		if (offers != null) {

			// set the layout and add the slid button listener
			setContentView(R.layout.activity_home);

			HomeScreenArrayAdapter<Offer> adapter = new HomeScreenArrayAdapter<Offer>(
					this, android.R.layout.simple_list_item_1, ((Offers)offers).getOffers());
			ListView listview = (ListView) findViewById(R.id.list);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(final AdapterView<?> parent, final View view,
						final int position, final long id) {
					
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							Offer offer = (Offer) parent.getItemAtPosition(position);
							Intent intent = new Intent(view.getContext(),
									OfferDetailActivity.class);
							try {
								OfferDetail offerDetail = ServiceFactory.offerService().getOfferDetail((User) user, offer.getSlug());
								intent.putExtra("offerDetail", offerDetail);
								view.getContext().startActivity(intent);
							} catch (InstamojoException e) {
								// log error!
								AndroidUtil.showAlert(view.getContext(), FAILED, NO_INTERNET);
							}
						}
					});
					thread.start();
				}
			});
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// do notihng!
	}
}
