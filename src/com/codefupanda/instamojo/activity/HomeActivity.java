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

import static com.codefupanda.instamojo.constant.Constants.NO_INTERNET;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codefupanda.instamojo.R;
import com.codefupanda.instamojo.exception.InstamojoException;
import com.codefupanda.instamojo.layout.AnimationLayout;
import com.codefupanda.instamojo.model.Offer;
import com.codefupanda.instamojo.model.User;
import com.codefupanda.instamojo.service.OfferService;
import com.codefupanda.instamojo.service.util.AndroidUtil;

/**
 * Activity for Home screen
 * 
 * @author shashank
 */
public class HomeActivity extends Activity implements AnimationLayout.Listener {

	private AnimationLayout mLayout;
	private Handler handler;

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
		handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				handler.post(new Runnable() { // This thread runs in the UI
					@Override
					public void run() {
						displayOffers(user);
					}
				});
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}


	private void displayOffers(Serializable user) {
		List<Offer> offers = null;
		try {
			OfferService officerService = new OfferService();
			offers = officerService.getAllOffers((User) user);
		} catch (InstamojoException e) {
			e.printStackTrace();
			AndroidUtil.showAlert(this, NO_INTERNET, null);
		}
		if (offers != null) {

			// set the layout and add the slid button listener
			setContentView(R.layout.activity_home);
			mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
			mLayout.setListener(this);

			HomeScreenArrayAdapter<Offer> adapter = new HomeScreenArrayAdapter<Offer>(
					this, android.R.layout.simple_list_item_1, offers);
			ListView listview = (ListView) findViewById(R.id.list);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Offer offer = (Offer) parent.getItemAtPosition(position);
					Intent intent = new Intent(view.getContext(),
							OfferDetail.class);
					intent.putExtra("offer", offer);
					view.getContext().startActivity(intent);
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

	// -------------------------- Side bar functions --------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mLayout.toggleSidebar();
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClickContentButton(View v) {
		mLayout.toggleSidebar();
	}

	@Override
	public void onBackPressed() {
		if (mLayout.isOpening()) {
			mLayout.closeSidebar();
		} else {
			finish();
		}
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarOpened() {
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarClosed() {
	}


	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
    @Override
    public boolean onContentTouchedWhenOpening() {
        // the content area is touched when sidebar opening, close sidebar
        mLayout.closeSidebar();
        return true;
    }
}
