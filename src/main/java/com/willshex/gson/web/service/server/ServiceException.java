//
//  ServiceException.java
//  willshex-gson-web-service-appengine
//
//  Created by William Shakour (billy1380) on 4 Dec 2013.
//  Copyright © 2013 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.server;

/**
 * @author billy1380
 * 
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {
	protected int code;

	public int getCode() {
		return code;
	}

	public ServiceException(int code, String message) {
		super(message);

		this.code = code;
	}

	public String toString() {
		return "code[" + String.valueOf(code) + "] = " + getClass().getSimpleName() + "[" + getMessage() + "]";
	}
}
