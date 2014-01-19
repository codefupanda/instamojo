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
package com.codefupanda.instamojo.service.util;

import static com.codefupanda.instamojo.constant.Constants.OK;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

/**
 * Contains Utilities for Android API
 * 
 * @author shashank
 */
public class AndroidUtil {
	
	public static void showAlert(Context context, String title, String message) {
		Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setPositiveButton(OK, null);
		alertDialog.show();
	}
}
