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
package com.codefupanda.instamojo.service.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * RestCallBuilder is for building REST service calls.
 * Provides method to prepare a rest call and execute.
 *  
 * @author Shashank Kulkarni (shashank.physics (@) gmail.com)
 */
public class RestCallBuilder {
	private String url;
	
	private Map<String, String> headers;
	private Map<String, String> params;
	
	private String response;
	private int responseCode;
	HttpsURLConnection connection;
	
	/**
	 * Construct RestClient for a URL
	 * @param url
	 */
	public RestCallBuilder(String url) {
		this.url = url;
		headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		params = new HashMap<String, String>();
	}
	
	/** 
	 * Execute the service call.
	 * 
	 * @param method
	 * @throws IOException
	 */
	public void execute(RequestMethod method) throws IOException {
		switch (method) {
		case GET:
			executeGet();
			break;
		
		case POST:
			executePost();
			break;
			
		default:
			break;
		}
	}

	/**
	 * Add header to the REST request
	 * 
	 * @param header header to be added
	 * @param value the value for the header
	 */
	public void addHeader(String header, String value) {
		headers.put(header, value);
	}
	
	/**
	 * Add Parameter to the REST request
	 * @param param the parameter name
	 * @param value the parameter value
	 */
	public void addParams(String param, String value) {
		params.put(param, value);
	}
	
	/**
	 * Do a Get request on the URL 
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void doGet() throws IOException {
		execute(RequestMethod.GET);
	}
	
	/**
	 * Do a Post request on the URL 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void doPost() throws IOException {
		execute(RequestMethod.POST);
	}
	
	/**
	 * Get the response 
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	
	/**
	 * Get response code
	 * @return the response code
	 */
	public int getResponseCode() {
		return responseCode;
	}
	
	/**
	 * Request Method. Get or Post
	 */
	public enum RequestMethod {
		GET("GET"), POST("POST");
		
		private String method;
		
		private RequestMethod(String method) {
			this.method = method;
		}
		
		public String getMethod() {
			return method;
		}
	}

	private void executePost() throws IOException {
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				connection.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
		}
        connection.setRequestMethod(RequestMethod.POST.getMethod());
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        StringBuilder requestString = new StringBuilder();
		updateRequestString(requestString);
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(requestString.toString());
		wr.flush();
		wr.close();
		
		executeRequest(connection);
	}

	private void executeGet() throws IOException {
        StringBuilder paramsString = new StringBuilder(url);
        if (params != null && params.size() > 0) {
        	paramsString.append("?");
        	updateRequestString(paramsString);
        }
        
        HttpsURLConnection connection = (HttpsURLConnection) new URL(paramsString.toString()).openConnection();
        connection.setRequestMethod(RequestMethod.GET.getMethod());
        
        if (headers != null  && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				connection.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
		}
        executeRequest(connection);
	}

	private void updateRequestString(StringBuilder paramsString)
			throws UnsupportedEncodingException {
		boolean firstIterate = true;
		for (Map.Entry<String, String> entry: params.entrySet()) {
			
			if (!firstIterate) {
				paramsString.append("&");
			}
			firstIterate = false;
			
			paramsString.append(entry.getKey());
			paramsString.append("=");
			paramsString.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
		}
	}
	
	private void executeRequest(HttpsURLConnection connection)
			throws IOException {
		responseCode = connection.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		this.response = response.toString();
	}
	
}
