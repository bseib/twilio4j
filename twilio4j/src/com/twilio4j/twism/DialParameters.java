package com.twilio4j.twism;

import com.twilio4j.twism.TwilioParameters.DialCallStatus;

public interface DialParameters {
	public String getDialCallSid();
	public DialCallStatus getDialCallStatus();
	public int getDialCallDuration();
}
