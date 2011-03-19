/*
 * Copyright 2011 broc.seib@gentomi.com
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
 */
package com.twilio4j.twism;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class TwilioStateMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final static private Logger logger = Logger.getLogger(TwilioStateMachineServlet.class.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advanceToNextState(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advanceToNextState(req, resp);
	}

	private void advanceToNextState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String SECRET = getFortyCharacterSecret();
		
		// pathInfo will indicate which handler of the state machine we will use.
		String pathInfo = req.getPathInfo();

		// recover the serialized user parameters from the cookie and build the TwilioParameters obj
		try {
			HashMap<String, String> userParams;
			CookieTwism cookieIn = CookieTwism.checkForCookie(req, SECRET);
			printTestCookie(req);
			if ( cookieIn == null ) {
				// none set yet. That means this is the first visit and user params are empty.
				logger.info("no cookie yet");
				userParams = new HashMap<String, String>();
			} else {
				logger.info("found existing cookie with these params:");
				userParams = cookieIn.recoverUserParamsFromCookiePayload(SECRET);
				for ( String k : userParams.keySet() ) { logger.info(k+"="+userParams.get(k)); }
			}
			TwilioParameters tp = new TwilioParameters(req, resp, userParams);

			// ask our subclass to find the right handler to execute, and return TwiML.
			// If null is returned, then only a callback was executed and we should
			// not return anything in the body. Of course an exception could be thrown
			// in which case an error code response should be returned.
			String twiml = advanceState(pathInfo, tp);
			logger.info("twiml returned: " + twiml);
			if ( twiml != null ) {
				resp.setContentType("text/xml");
//				CookieTwism cookieOut = new CookieTwism(tp.getUserParams());
//				CookieTwism.setHttpCookie(resp, cookieOut, SECRET);
				resp.addCookie(createTestCookie("test payload " + new Date().toString()));
				resp.setHeader("Cache-Control", "no-cache");
				resp.getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				resp.getWriter().println(twiml);
			} else {
				resp.setStatus(HttpServletResponse.SC_OK);
			}
			resp.flushBuffer(); // twilio didn't receive all my document
		}
		catch (CookieTamperedException e) {
			throw new ServletException(e.getMessage());
		}
	}
	
	private Cookie createTestCookie(String payload) {
		Cookie c = new Cookie("cookie_name", payload);
		c.setMaxAge(-1); // will not be persisted. will be deleted when browser exits
		c.setPath("/"); // this cookie is good everywhere on the site
		return c;
	}
	
	private void printTestCookie(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if ( cookies != null ) {
			for ( Cookie c : cookies ) {
				if ( "cookie_name".equals(c.getName()) ) {
					logger.info("test cookie says: " + c.getValue());
					break;
				}
			}
		}
	}
	

	abstract protected String getFortyCharacterSecret();
	abstract protected String advanceState(String pathInfo, TwilioParameters tp) throws ServletException;
	
}
