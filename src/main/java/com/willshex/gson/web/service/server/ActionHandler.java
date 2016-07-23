//
//  ActionHandler.java
//  willshex-gson-web-service-appengine
//
//  Created by William Shakour on 4 Jul 2013.
//  Copyright © 2013 WillShex Limited. All rights reserved.
//
package com.willshex.gson.web.service.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.willshex.gson.web.service.shared.Error;
import com.willshex.gson.web.service.shared.IClearSensitive;
import com.willshex.gson.web.service.shared.Request;
import com.willshex.gson.web.service.shared.Response;
import com.willshex.gson.web.service.shared.StatusType;

/**
 * @author billy1380
 * 
 */
public abstract class ActionHandler<I extends Request, O extends Response>
		implements IClearSensitive<O> {

	public O handle (I input) {
		final Logger logger = logger();

		if (logger.isLoggable(Level.FINER)) {
			logger().finer(
					"Entering " + this.getClass().getSimpleName() + ".handle");
		}

		O output = newOutput();

		try {
			handle(input, output);

			output.status = StatusType.StatusTypeSuccess;
		} catch (Exception e) {
			output.status = StatusType.StatusTypeFailure;
			output.error = convertToErrorAndLog(e);
		} finally {
			clearSensitiveFields(output);
		}

		if (logger.isLoggable(Level.FINER)) {
			logger.finer(
					"Exiting " + this.getClass().getSimpleName() + ".handle");
		}

		return output;
	}

	protected abstract void handle (I input, O output) throws Exception;

	protected abstract O newOutput ();

	protected abstract Logger logger ();

	protected Error convertToErrorAndLog (Exception e) {
		Error error = new Error();

		if (e instanceof ServiceException) {
			error.code = Integer.valueOf(((ServiceException) e).getCode());
			error.message = ((ServiceException) e).getMessage();
		} else {
			error.code = Integer.valueOf(888);
			error.message = "An unexpected error occured [" + e.toString()
					+ "]";
		}

		logger().log(Level.SEVERE, error.message, e);

		return error;
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.willshex.gson.web.service.shared.IClearSensitive#clearSensitiveFields
	 * (com.willshex.gson.web.service.shared.Response) */
	@Override
	public void clearSensitiveFields (O output) {}

}
