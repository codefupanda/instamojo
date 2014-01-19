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

import com.google.gson.Gson;

/**
 * Json Utils.
 * Contains methods for json conversion
 * 
 * @author shashank
 */
public class JsonUtil {
	
	/**
	 * Convert Object to Json string
	 * @param object the object to be converted to Json
	 * @return the converted Json
	 */
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	/**
	 * Convert Json String to Object
	 * @param json The Json String
	 * @param clasz the class of object to be converted
	 * @return converted Object
	 */
	public static <T> T toObject(String json, Class<T> clasz) {
		return new Gson().fromJson(json, clasz);
	}
}
