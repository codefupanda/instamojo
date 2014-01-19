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

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Used for displaying Offers list on the Home screen
 * 
 * @author shashank
 */
public class HomeScreenArrayAdapter<T> extends ArrayAdapter<T> {

	public HomeScreenArrayAdapter(Context context, int resource, List<T> offers) {
		super(context, resource, offers);
	}
	
	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}
}
