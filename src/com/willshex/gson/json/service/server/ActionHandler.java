//
//  ActionHandler.java
//  storedata
//
//  Created by William Shakour on 4 Jul 2013.
//  Copyright © 2013 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.willshex.gson.json.service.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.willshex.gson.json.service.shared.Error;

/**
 * @author billy1380
 * 
 */
public abstract class ActionHandler {

	protected Error convertToErrorAndLog(Logger log, Exception e) {
		Error error = new Error();

		if (e instanceof InputValidationException) {
			error.code = Integer.valueOf(((InputValidationException) e).getCode());
			error.message = ((InputValidationException) e).getMessage();
		} else {
			error.code = Integer.valueOf(888);
			error.message = "An unexpected error occured [" + e.toString() + "]";
		}

		log.log(Level.SEVERE, error.message, e);

		return error;
	}
}
