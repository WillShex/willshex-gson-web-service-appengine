//
//  InputValidationException.java
//  jspacecloud
//
//  Created by William Shakour on Jun 15, 2012.
//  Copyright © 2012 Spacehopper Studios Ltd. All rights reserved.
//
package com.willshex.gson.json.service.server;

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
