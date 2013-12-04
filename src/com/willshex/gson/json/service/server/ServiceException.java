//
//  ServiceException.java
//  spchopr.gson.json.service_appengine
//
//  Created by William Shakour (billy1380) on 4 Dec 2013.
//  Copyright © 2013 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.willshex.gson.json.service.server;

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
