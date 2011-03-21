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
package com.twilio4j.twiml;

import com.twilio4j.twism.Method;

/**
 * This class directly reflects the Conference verb documented at
 * http://www.twilio.com/docs/api/twiml/conference
 * 
 * All of the descriptions included in these javadoc comments
 * come directly from the twilio website.
 * 
 * @author broc.seib@gentomi.com
 */
public class Conference<E extends Enum<?>> extends TwiML implements NestInDial {

	private String roomName;
	private Boolean muted;
	private Boolean beep;
	private Boolean startConferenceOnEnter;
	private Boolean endConferenceOnExit;
	private E waitUrl;
	private Method waitMethod;
	private Integer maxParticipants;
	
	/**
	 * Converts this object into XML. This function is normally called by the state
	 * machine servlet and not called directly by you.
	 */
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
