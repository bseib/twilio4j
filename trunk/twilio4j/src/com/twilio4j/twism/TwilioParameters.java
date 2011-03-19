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

	
}
