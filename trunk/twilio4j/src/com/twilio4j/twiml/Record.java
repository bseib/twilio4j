package com.twilio4j.twiml;

import com.twilio4j.twism.Method;

public class Record<E extends Enum<?>> extends TwiML {

	private E action;
	private Method method;
	private Integer timeout;
	private Character finishOnKey; // TODO this *could* have user input that needs escaped so that it doesn't break the twiml parsing. don't hook it up to unvalidated user input!
	private Integer maxLength;
	private Boolean transcribe;
	private E transcribeCallback;
	private Boolean playBeep;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Record");
		if ( action != null ) { buf.append(" action=\"").append(baseUrl).append(action.name()).append("\""); }
		if ( method != null ) { buf.append(" method=\"").append(method.name()).append("\""); }
		if ( timeout != null ) { buf.append(" timeout=\"").append(timeout.toString()).append("\""); }
		if ( finishOnKey != null ) { buf.append(" finishOnKey=\"").append(finishOnKey.charValue()).append("\""); }
		if ( maxLength != null ) { buf.append(" maxLength=\"").append(maxLength.toString()).append("\""); }
		if ( transcribe != null ) { buf.append(" transcribe=\"").append(transcribe.toString()).append("\""); }
		if ( transcribeCallback != null ) { buf.append(" transcribeCallback=\"").append(baseUrl).append(transcribeCallback.name()).append("\""); }
		if ( playBeep != null ) { buf.append(" playBeep=\"").append(playBeep.toString()).append("\""); }
		buf.append("/>");
	}
	
	public Record<E> action(E action) {
		this.action = action;
		return this;
	}
	
	public Record<E> method(Method method) {
		this.method = method;
		return this;
	}
	public Record<E> methodPOST() { return method(Method.POST); }
	public Record<E> methodGET() { return method(Method.GET); }
	
	public Record<E> timeout(int secondsToPressNextDigit) {
		this.timeout = secondsToPressNextDigit;
		return this;
	}
	
	public Record<E> finishOnKey(char key) {
		this.finishOnKey = key;
		return this;
	}
	public Record<E> finishOnKeyHash() { return finishOnKey('#'); }
	public Record<E> finishOnKeyStar() { return finishOnKey('*'); }
	public Record<E> finishOnKeyZero() { return finishOnKey('0'); }
	public Record<E> finishOnKeyOne() { return finishOnKey('1'); }
	public Record<E> finishOnKeyTwo() { return finishOnKey('2'); }
	public Record<E> finishOnKeyThree() { return finishOnKey('3'); }
	public Record<E> finishOnKeyFour() { return finishOnKey('4'); }
	public Record<E> finishOnKeyFive() { return finishOnKey('5'); }
	public Record<E> finishOnKeySix() { return finishOnKey('6'); }
	public Record<E> finishOnKeySeven() { return finishOnKey('7'); }
	public Record<E> finishOnKeyEight() { return finishOnKey('8'); }
	public Record<E> finishOnKeyNine() { return finishOnKey('9'); }

	public Record<E> maxLength(int secondsToRecord) {
		this.timeout = secondsToRecord;
		return this;
	}

	public Record<E> transcribe(boolean doTranscription) {
		this.transcribe = doTranscription;
		return this;
	}

	public Record<E> transcribeCallback(E transcribeCallback) {
		this.transcribeCallback = transcribeCallback;
		return this;
	}

	public Record<E> playBeep(boolean doPlayBeep) {
		this.playBeep = doPlayBeep;
		return this;
	}

	public E getAction() {
		return action;
	}
	public Method getMethod() {
		return method;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public Character getFinishOnKey() {
		return finishOnKey;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public Boolean getTranscribe() {
		return transcribe;
	}
	public E getTranscribeCallback() {
		return transcribeCallback;
	}
	public Boolean getPlayBeep() {
		return playBeep;
	}

}
