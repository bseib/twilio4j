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

public class Sms<E extends Enum<?>> extends TwiML {

	private String message;
	private String to;
	private String from; // TODO to and from *could* have user input that needs escaped so that it doesn't break the twiml parsing. don't hook it up to unvalidated user input!
	private E action;
	private Method method;
	private E statusCallback;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Sms");
		if ( to != null ) { buf.append(" to=\"").append(to).append("\""); }
		if ( from != null ) { buf.append(" from=\"").append(from).append("\""); }
		if ( action != null ) { buf.append(" action=\"").append(baseUrl).append(action.name()).append("\""); }
		if ( method != null ) { buf.append(" method=\"").append(method.name()).append("\""); }
		if ( statusCallback != null ) { buf.append(" statusCallback=\"").append(baseUrl).append(statusCallback.name()).append("\""); }
		buf.append('>');
		buf.append(escape(message));
		buf.append("</Sms>");
	}

	public Sms(String message) {
		this.message = message;
	}
	
	public Sms<E> to(String to) {
		this.to = to;
		return this;
	}
	
	public Sms<E> from(String from) {
		this.from = from;
		return this;
	}
	
	public Sms<E> action(E action) {
		this.action = action;
		return this;
	}
	
	public Sms<E> method(Method method) {
		this.method = method;
		return this;
	}
	public Sms<E> methodPOST() { return method(Method.POST); }
	public Sms<E> methodGET() { return method(Method.GET); }
	
	public Sms<E> statusCallback(E statusCallback) {
		this.statusCallback = statusCallback;
		return this;
	}

	public String getTextBody() {
		return message;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}
	public E getAction() {
		return action;
	}
	public Method getMethod() {
		return method;
	}
	public E getStatusCallback() {
		return statusCallback;
	}

}
