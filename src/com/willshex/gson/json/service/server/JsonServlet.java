//
//  JsonServlet.java
//  jspacecloud
//
//  Created by William Shakour on June 16, 2012.
//  Copyright © 2012 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.willshex.gson.json.service.server;

import static com.spacehopperstudios.utility.StringUtils.stripslashes;
import static com.spacehopperstudios.utility.StringUtils.urldecode;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.willshex.service.ContextAwareServelet;

@SuppressWarnings("serial")
public abstract class JsonServlet extends ContextAwareServelet {

	private final Class<?> thisClass = getClass();
	private final Logger LOG = Logger.getLogger(thisClass.getName());

	// protected boolean allowXDomainPosting = false;

	@Override
	protected void doGet() throws IOException {

		String output = "null";

		String action = REQUEST.get().getParameter("action");
		String request = REQUEST.get().getParameter("request");

		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Action is [" + action + "] and request is [" + request + "]");
		}

		if (action != null && request != null) {
			request = stripslashes(urldecode(request));

			output = processAction(action, (JsonObject) (new JsonParser()).parse(request));

			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine("Output is [" + output + "]");
			}

			// relaced RESPONSE.get().setContentType("application/x-javascript"); from
			// google example
			RESPONSE.get().setContentType("application/json; charset=utf-8");
			RESPONSE.get().setHeader("Cache-Control", "no-cache");

			// if (allowXDomainPosting) {
			// RESPONSE.get().setHeader("Access-Control-Allow-Origin", "*");
			// }

			String acceptEncoding = REQUEST.get().getHeader("Accept-Encoding");
			if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
				RESPONSE.get().setHeader("Content-Encoding", "gzip");

				OutputStreamWriter writer = null;
				try {
					GZIPOutputStream outputStream = new GZIPOutputStream(RESPONSE.get().getOutputStream());
					writer = new OutputStreamWriter(outputStream);
					writer.append(output);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			} else {
				RESPONSE.get().getWriter().println(/* "var response=" . */output);
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
		doGet(req, resp);
	};

	protected abstract String processAction(String action, JsonObject request);
}
