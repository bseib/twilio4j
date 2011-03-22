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
	
	public TwilioParameters(HttpServletRequest req, HttpServletResponse resp, HashMap<String, String> userParams) {
		this.req = req;
		this.resp = resp;
		this.userParams = userParams;
	}

	public HttpServletRequest getRequest() {
		return req;
	}
	public HttpServletResponse getResponse() {
		return resp;
	}
	public HashMap<String, String> getUserParams() {
		return userParams;
	}
	
	/*
	 * Standard Voice Request Params
	 */
	public String getCallSid() { return req.getParameter("CallSid"); }
	public String getAccountSid() { return req.getParameter("AccountSid"); }
	public String getFrom() { return req.getParameter("From"); }
	public String getTo() { return req.getParameter("To"); }
	public CallStatus getCallStatus() {
		return CallStatus.valueOf( req.getParameter("CallStatus").replace('-', '_') );
	}
	public String getApiVersion() { return req.getParameter("ApiVersion"); }
	public String getDirection() { return req.getParameter("Direction"); }
	public String getForwardedFrom() { return req.getParameter("ForwardedFrom"); }
	// these geo params *might* be present too
	public String getFromCity() { return req.getParameter("FromCity"); }
	public String getFromState() { return req.getParameter("FromState"); }
	public String getFromZip() { return req.getParameter("FromZip"); }
	public String getFromCountry() { return req.getParameter("FromCountry"); }
	public String getToCity() { return req.getParameter("ToCity"); }
	public String getToState() { return req.getParameter("ToState"); }
	public String getToZip() { return req.getParameter("ToZip"); }
	public String getToCountry() { return req.getParameter("ToCountry"); }

	// present for StatusCallback requests
	public String getCallDuration() { return req.getParameter("CallDuration"); }

	// for Gather Parameters
	public GatherParameters Gather() {
		return new GatherParameters() {
			public String getDigits() { return req.getParameter("Digits"); }
		};
	}
	
	// for Record Parameters
	public RecordParameters Record() {
		return new RecordParameters() {
			public String getDigits() { return req.getParameter("Digits"); }
			public int getRecordingDuration() { return Integer.parseInt(req.getParameter("RecordingDuration")); }
			public String getRecordingUrl() { return req.getParameter("RecordingUrl"); }
			public boolean isHangup() { return "hangup".equals(getDigits()); }
		};
	}
	
	// for Sms Parameters
	public SmsParameters Sms() {
		return new SmsParameters() {
			public String getSmsSid() { return req.getParameter("SmsSid"); }
			public String getSmsStatus() { return req.getParameter("SmsStatus"); }
		};
	}
	
	// for Dial Parameters
	public DialParameters Dial() {
		return new DialParameters() {
			public String getDialCallSid() { return req.getParameter("DialCallSid"); }
			public DialCallStatus getDialCallStatus() {
				return DialCallStatus.valueOf( req.getParameter("DialCallStatus").replace('-', '_') );
			}
			public int getDialCallDuration() { return Integer.parseInt(req.getParameter("DialCallDuration")); }
		};
	}

	// for Transcribe Callback Parameters (from Record)
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
