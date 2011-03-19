package com.twilio4j.twiml;

import com.twilio4j.twism.Method;


public class Redirect<E extends Enum<?>> extends TwiML {

	private E nextState;
	private Method method;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Redirect");
		if ( method != null ) { buf.append(" method=\"").append(method.name()).append("\""); }
		buf.append('>');
		buf.append(baseUrl).append(nextState.name());
		buf.append("</Redirect>");
	}
	
	public Redirect(E nextState) {
		this.nextState = nextState;
	}
	
	public Redirect<E> method(Method method) {
		this.method = method;
		return this;
	}
	public Redirect<E> methodPOST() { return method(Method.POST); }
	public Redirect<E> methodGET() { return method(Method.GET); }

	public E getNextState() {
		return nextState;
	}
	public Method getMethod() {
		return method;
	}

}
