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

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>TwilioParameters are passed to your action and callback handlers. They contain all of
 * the standard Twilio parameters, documented 
 * <a href="http://www.twilio.com/docs/api/twiml/twilio_request">here</a>. It explains:</p>
 * 
 * <p>Twilio makes HTTP requests to your application just like a regular web browser. By including parameters and values in its requests, Twilio sends data to your application that you can act upon before responding. You can configure the URLs and HTTP Methods Twilio uses to make its requests via your account portal or using the REST API.</p>
 * 
 * <p>When Twilio receives a call for one of your Twilio numbers it makes a synchronous HTTP request to the Voice URL configured for that number, and expects to receive TwiML in response. Twilio sends the following parameters with its request as POST parameters or URL query parameters, depending on which HTTP method you've configured:</p>
 * 
 * @author broc.seib@gentomi.com
 *
 */
public class TwilioParameters {
	
	public enum CallStatus {
		queued,
		ringing,
		in_progress,
		completed,
		busy,
		failed,
		no_answer,
		canceled
	}
	
	public enum DialCallStatus {
		completed,
		busy,
		no_answer,
		failed,
		canceled
	}

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HashMap<String, String> userParams;
	
	TwilioParameters(HttpServletRequest req, HttpServletResponse resp, HashMap<String, String> userParams) {
		this.req = req;
		this.resp = resp;
		this.userParams = userParams;
	}

	/**
	 * Sometimes you need to get to the original request object.
	 * @return the raw HttpServletRequest object.
	 */
	public HttpServletRequest getRequest() {
		return req;
	}
	/**
	 * Sometimes you need to get to the original response object.
	 * @return the raw HttpServletResponse object.
	 */
	public HttpServletResponse getResponse() {
		return resp;
	}
	/**
	 * Twilio4j maintains a HashMap of "user parameters", persisting it as a cookie to the
	 * twilio client. On subsequent interactions with your servlet, this set of userParams
	 * is passed to your action and callback handlers such that you can modify its state.
	 * It is in this manner that you should maintain state in your call flow. Of course you
	 * are limited by the 4k limit of a cookie, and there is some overhead in the escaping
	 * and encoding and digital signing of the userParams in cookie form. So if you for
	 * some reason need to keep more than ~2K of data, consider storing an opaque key in
	 * the userParams, and persisting the actual data elsewhere.
	 *   
	 * @return the HashMap of userParams out of the cookie from the current caller.
	 */
	public HashMap<String, String> getUserParams() {
		return userParams;
	}
	

	// Standard Voice Request Params

	/**
	 * @return A unique identifier for this call, generated by Twilio.
	 */
	public String getCallSid() { return req.getParameter("CallSid"); }
	/**
	 * @return Your Twilio account id. It is 34 characters long, and always starts with the letters AC.
	 */
	public String getAccountSid() { return req.getParameter("AccountSid"); }
	/**
	 * @return The phone number of the party that initiated the call. Formatted with a '+' and country code e.g., +16175551212 (E.164 format). If the call is inbound, then it is the caller's caller ID. If the call is outbound, i.e., initiated via a request to the REST API, then this is the phone number you specify as the caller ID.
	 */
	public String getFrom() { return req.getParameter("From"); }
	/**
	 * @return The phone number of the called party. Formatted with a '+' and country code e.g., +16175551212 (E.164 format). If the call is inbound, then it's your Twilio phone number. If the call is outbound, then it's the phone number you provided to call.
	 */
	public String getTo() { return req.getParameter("To"); }
	/**
	 * @return A descriptive status for the call. The value is one of queued, ringing, in-progress, completed, busy, failed or no-answer. These values are enumerated in {@link CallStatus}.
	 */
	public CallStatus getCallStatus() {
		return CallStatus.valueOf( req.getParameter("CallStatus").replace('-', '_') );
	}
	/**
	 * @return The version of the Twilio API used to handle this call. For incoming calls, this is determined by the API version set on the called number. For outgoing calls, this is the API version used by the outgoing call's REST API request.
	 */
	public String getApiVersion() { return req.getParameter("ApiVersion"); }
	/**
	 * @return Indicates the direction of the call. In most cases this will be inbound, but if you are using <Dial> it will be outbound-dial.
	 */
	public String getDirection() { return req.getParameter("Direction"); }
	/**
	 * @return This parameter is set only when Twilio receives a forwarded call, but its value depends on the caller's carrier including information when forwarding. Not all carriers support passing this information.
	 */
	public String getForwardedFrom() { return req.getParameter("ForwardedFrom"); }

	// these geo params *might* be present too
	/**
	 * @return The city of the caller.
	 */
	public String getFromCity() { return req.getParameter("FromCity"); }
	/**
	 * @return The state or province of the caller.
	 */
	public String getFromState() { return req.getParameter("FromState"); }
	/**
	 * @return The postal code of the caller.
	 */
	public String getFromZip() { return req.getParameter("FromZip"); }
	/**
	 * @return The country of the caller.
	 */
	public String getFromCountry() { return req.getParameter("FromCountry"); }
	/**
	 * @return The city of the called party.
	 */
	public String getToCity() { return req.getParameter("ToCity"); }
	/**
	 * @return The state or province of the called party.
	 */
	public String getToState() { return req.getParameter("ToState"); }
	/**
	 * @return The postal code of the called party.
	 */
	public String getToZip() { return req.getParameter("ToZip"); }
	/**
	 * @return The country of the called party.
	 */
	public String getToCountry() { return req.getParameter("ToCountry"); }

	// present for StatusCallback requests
	/**
	 * CallDuration parameter is present in StatusCallback requests. After receiving a call, requesting TwiML from your app, processing it, and finally ending the call, Twilio will make an asynchronous HTTP request to the StatusCallback URL configured for the called Twilio number (if there is one). By providing a StatusCallback URL for your Twilio number and capturing this request you can determine when a call ends and receive information about the call.
	 * @return The duration in seconds of the just-completed call.
	 */
	public String getCallDuration() { return req.getParameter("CallDuration"); }

	/**
	 * To access parameters posted by the action of the {@link Gather} verb.
	 * @return {@link GatherParameters}
	 */
	public GatherParameters Gather() {
		return new GatherParameters() {
			public String getDigits() { return req.getParameter("Digits"); }
		};
	}
	
	/**
	 * To access parameters posted by the action of the {@link Record} verb.
	 * @return {@link RecordParameters}
	 */
	public RecordParameters Record() {
		return new RecordParameters() {
			public String getDigits() { return req.getParameter("Digits"); }
			public int getRecordingDuration() { return Integer.parseInt(req.getParameter("RecordingDuration")); }
			public String getRecordingUrl() { return req.getParameter("RecordingUrl"); }
			public boolean isHangup() { return "hangup".equals(getDigits()); }
		};
	}
	
	/**
	 * To access parameters posted by the action of the {@link Sms} verb.
	 * @return {@link SmsParameters}
	 */
	public SmsParameters Sms() {
		return new SmsParameters() {
			public String getSmsSid() { return req.getParameter("SmsSid"); }
			public String getSmsStatus() { return req.getParameter("SmsStatus"); }
		};
	}
	
	/**
	 * To access parameters posted by the action of the {@link Dial} verb.
	 * @return {@link DialParameters}
	 */
	public DialParameters Dial() {
		return new DialParameters() {
			public String getDialCallSid() { return req.getParameter("DialCallSid"); }
			public DialCallStatus getDialCallStatus() {
				return DialCallStatus.valueOf( req.getParameter("DialCallStatus").replace('-', '_') );
			}
			public int getDialCallDuration() { return Integer.parseInt(req.getParameter("DialCallDuration")); }
		};
	}

	/**
	 * To access parameters posted by the transcribe callback of the {@link Record} verb.
	 * @return {@link TranscribeParameters}
	 */
	public TranscribeParameters TranscribeCallback() {
		return new TranscribeParameters() {
			public String getRecordingUrl() { return req.getParameter("RecordingUrl"); }
			public TranscriptionStatus getTranscriptionStatus() {
				return TranscriptionStatus.valueOf( req.getParameter("TranscriptionStatus") );
			}
			public String getTranscriptionText() { return req.getParameter("TranscriptionText"); }
			public String getTranscriptionUrl() { return req.getParameter("TranscriptionUrl"); }
		};
	}

	
}
