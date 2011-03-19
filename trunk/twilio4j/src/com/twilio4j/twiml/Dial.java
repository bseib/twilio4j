package com.twilio4j.twiml;

import com.twilio4j.twism.Method;

public class Dial<E extends Enum<?>> extends TwiML {

	private NestInDial[] nestedNouns;
	
	private E action;
	private Method method;
	private Integer timeout;
	private Boolean hangupOnStar;
	private Integer timeLimit;
	private String callerId;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Dial");
		if ( action != null ) { buf.append(" action=\"").append(baseUrl).append(action.name()).append("\""); }
		if ( method != null ) { buf.append(" method=\"").append(method.name()).append("\""); }
		if ( timeout != null ) { buf.append(" timeout=\"").append(timeout.toString()).append("\""); }
		if ( hangupOnStar != null ) { buf.append(" hangupOnStar=\"").append(hangupOnStar.toString()).append("\""); }
		if ( timeLimit != null ) { buf.append(" timeLimit=\"").append(timeLimit.toString()).append("\""); }
		if ( callerId != null ) { buf.append(" callerId=\"").append(callerId).append("\""); }
		buf.append('>');
		for ( ToXML t : nestedNouns ) {
			t.toXml(buf, baseUrl);
		}
		buf.append("</Dial>");
	}
	
	public Dial(NestInDial... nestedNouns) {
		this.nestedNouns = nestedNouns;
	}
	
	public Dial<E> action(E action) {
		this.action = action;
		return this;
	}
	
	public Dial<E> method(Method method) {
		this.method = method;
		return this;
	}
	public Dial<E> methodPOST() { return method(Method.POST); }
	public Dial<E> methodGET() { return method(Method.GET); }
	
	public Dial<E> timeout(int secondsToWaitForAnswer) {
		this.timeout = secondsToWaitForAnswer;
		return this;
	}
	
	public Dial<E> hangupOnStar(boolean doHangupOnStar) {
		this.hangupOnStar = doHangupOnStar;
		return this;
	}
	
	public Dial<E> timeLimit(int maxSecondsDurationOfCall) {
		this.timeout = maxSecondsDurationOfCall;
		return this;
	}
	
	public Dial<E> callerId(String callerId) {
		this.callerId = callerId;
		return this;
	}

	public NestInDial[] getNestedNouns() {
		return nestedNouns;
	}

	public void setNestedNouns(NestInDial[] nestedNouns) {
		this.nestedNouns = nestedNouns;
	}

	public E getAction() {
		return action;
	}

	public void setAction(E action) {
		this.action = action;
	}

	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Boolean getHangupOnStar() {
		return hangupOnStar;
	}
	public void setHangupOnStar(Boolean hangupOnStar) {
		this.hangupOnStar = hangupOnStar;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getCallerId() {
		return callerId;
	}
	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

}
