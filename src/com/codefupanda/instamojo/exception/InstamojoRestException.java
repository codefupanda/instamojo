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
package com.codefupanda.instamojo.exception;

/**
 * Rest call exceptions
 * 
 * @author shashank
 */
public class InstamojoRestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public InstamojoRestException() {
		super();
	}

	/**
	 * @param detailMessage
	 * @param throwable
	 */
	public InstamojoRestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	/**
	 * @param detailMessage
	 */
	public InstamojoRestException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * @param throwable
	 */
	public InstamojoRestException(Throwable throwable) {
		super(throwable);
	}
	
	
}
