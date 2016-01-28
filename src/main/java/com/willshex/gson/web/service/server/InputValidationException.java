//
//  InputValidationException.java
//  willshex-gson-web-service-appengine
//
//  Created by William Shakour on Jun 15, 2012.
//  Copyright © 2012 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.server;

/**
 * @author William Shakour
 * 
 */
@SuppressWarnings("serial")
public class InputValidationException extends ServiceException {

	/**
	 * @param code
	 * @param message
	 */
	public InputValidationException(int code, String message) {
		super(code, message);
	}

}
