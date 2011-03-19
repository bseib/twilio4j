package com.twilio4j.twism;

public interface RecordParameters {
	public String getRecordingUrl();
	public int getRecordingDuration();
	public String getDigits();
	public boolean isHangup();
}
