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

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.codefupanda.instamojo.R;
import com.codefupanda.instamojo.model.OfferDetail;

/**
 * Screen for Offer Details.
 * 
 * @author shashank
 */
public class OfferDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offer_detail);
		OfferDetail detail = (OfferDetail) this.getIntent().getExtras().getSerializable("offerDetail");
		
		TableLayout layout = (TableLayout) findViewById(R.id.offerDetailsTableLayout);
		android.widget.TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),
	                "fonts/Roboto-Black.ttf");
		
		layout.addView(getRow("Title ", detail.getOffer().getTitle(), tf), layoutParams);
		layout.addView(getRow("Description ", detail.getOffer().getDescription(), tf), layoutParams);
		layout.addView(getRow("Price ", detail.getOffer().getCurrency() + " " + detail.getOffer().getBasePrice(), tf), layoutParams);
		layout.addView(getRow("Note", detail.getOffer().getNote(), tf), layoutParams);
		
	}

	private TableRow getRow(String name, String value, Typeface tf) {
		// create a new TableRow
        TableRow row = new TableRow(this);
        
        TextView textView = new TextView(this);
        textView.setText(name);
        textView.setTypeface(tf);
        row.addView(textView);
        
        textView = new TextView(this);
        textView.setText(value);
        textView.setTypeface(tf);
        row.addView(textView);
        
		return row;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offer_detail, menu);
		return true;
	}

}
