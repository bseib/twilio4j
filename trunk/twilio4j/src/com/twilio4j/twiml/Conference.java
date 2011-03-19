package com.twilio4j.twiml;

import com.twilio4j.twism.Method;

public class Conference<E extends Enum<?>> extends TwiML implements NestInDial {

	private String roomName;
	private Boolean muted;
	private Boolean beep;
	private Boolean startConferenceOnEnter;
	private Boolean endConferenceOnExit;
	private E waitUrl;
	private Method waitMethod;
	private Integer maxParticipants;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Conference");
		if ( muted != null ) { buf.append(" muted=\"").append(muted.toString()).append("\""); }
		if ( beep != null ) { buf.append(" beep=\"").append(beep.toString()).append("\""); }
		if ( startConferenceOnEnter != null ) { buf.append(" startConferenceOnEnter=\"").append(startConferenceOnEnter.toString()).append("\""); }
		if ( endConferenceOnExit != null ) { buf.append(" endConferenceOnExit=\"").append(endConferenceOnExit.toString()).append("\""); }
		if ( waitUrl != null ) { buf.append(" waitUrl=\"").append(baseUrl).append(waitUrl.name()).append("\""); }
		if ( waitMethod != null ) { buf.append(" waitMethod=\"").append(waitMethod.name()).append("\""); }
		if ( maxParticipants != null ) { buf.append(" maxParticipants=\"").append(maxParticipants.toString()).append("\""); }
		buf.append('>');
		buf.append(escape(roomName));
		buf.append("</Conference>");
	}

	public Conference(String roomName) {
		this.roomName = roomName;
	}
	
	public Conference<E> muted(boolean muted) {
		this.muted = muted;
		return this;
	}
	public Conference<E> beep(boolean beep) {
		this.beep = beep;
		return this;
	}
	public Conference<E> startConferenceOnEnter(boolean startConferenceOnEnter) {
		this.startConferenceOnEnter = startConferenceOnEnter;
		return this;
	}
	public Conference<E> endConferenceOnExit(boolean endConferenceOnExit) {
		this.endConferenceOnExit = endConferenceOnExit;
		return this;
	}
	
	public Conference<E> waitUrl(E waitUrl) {
		this.waitUrl = waitUrl;
		return this;
	}

	public Conference<E> waitMethod(Method waitMethod) {
		this.waitMethod = waitMethod;
		return this;
	}
	public Conference<E> waitMethodPOST() { return waitMethod(Method.POST); }
	public Conference<E> waitMethodGET() { return waitMethod(Method.GET); }
	
	public Conference<E> maxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
		return this;
	}

	public String getRoomName() {
		return roomName;
	}
	public Boolean getMuted() {
		return muted;
	}
	public Boolean getBeep() {
		return beep;
	}
	public Boolean getStartConferenceOnEnter() {
		return startConferenceOnEnter;
	}
	public Boolean getEndConferenceOnExit() {
		return endConferenceOnExit;
	}
	public E getWaitUrl() {
		return waitUrl;
	}
	public Method getWaitMethod() {
		return waitMethod;
	}
	public Integer getMaxParticipants() {
		return maxParticipants;
	}

}
