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
 * This class directly reflects the Gather verb documented at
 * http://www.twilio.com/docs/api/twiml/gather
 * 
 * All of the descriptions included in these javadoc comments
 * come directly from the twilio website.
 * 
 * @author broc.seib@gentomi.com
 */
public class Gather<E extends Enum<?>> extends TwiML {
	
	private NestInGather nestedVerbs[];
	
	private E action;
	private Method method;
	private Integer timeout;
	private Character finishOnKey; // TODO this *could* have user input that needs escaped so that it doesn't break the twiml parsing. don't hook it up to unvalidated user input!
	private Integer numDigits;
	
	/**
	 * Converts this object into XML. This function is normally called by the state
	 * machine servlet and not called directly by you.
	 */
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Gather");
		if ( action != null ) { buf.append(" action=\"").append(baseUrl).append(action.name()).append("\""); }
		if ( method != null ) { buf.append(" method=\"").append(method.name()).append("\""); }
		if ( timeout != null ) { buf.append(" timeout=\"").append(timeout.toString()).append("\""); }
		if ( finishOnKey != null ) { buf.append(" finishOnKey=\"").append(finishOnKey.charValue()).append("\""); }
		if ( numDigits != null ) { buf.append(" numDigits=\"").append(numDigits.toString()).append("\""); }
		buf.append('>');
		for ( ToXML t : nestedVerbs ) {
			t.toXml(buf, baseUrl);
		}
		buf.append("</Gather>");
	}

	public Gather(NestInGather... verbs) {
		this.nestedVerbs = verbs;
	}
	
	public Gather<E> action(E action) {
		this.action = action;
		return this;
	}
	
	public Gather<E> method(Method method) {
		this.method = method;
		return this;
	}
	public Gather<E> methodPOST() { return method(Method.POST); }
	public Gather<E> methodGET() { return method(Method.GET); }
	
	public Gather<E> timeout(int secondsToPressNextDigit) {
		this.timeout = secondsToPressNextDigit;
		return this;
	}
	
	public Gather<E> finishOnKey(char key) {
		this.finishOnKey = key;
		return this;
	}
	public Gather<E> finishOnKeyHash() { return finishOnKey('#'); }
	public Gather<E> finishOnKeyStar() { return finishOnKey('*'); }
	public Gather<E> finishOnKeyZero() { return finishOnKey('0'); }
	public Gather<E> finishOnKeyOne() { return finishOnKey('1'); }
	public Gather<E> finishOnKeyTwo() { return finishOnKey('2'); }
	public Gather<E> finishOnKeyThree() { return finishOnKey('3'); }
	public Gather<E> finishOnKeyFour() { return finishOnKey('4'); }
	public Gather<E> finishOnKeyFive() { return finishOnKey('5'); }
	public Gather<E> finishOnKeySix() { return finishOnKey('6'); }
	public Gather<E> finishOnKeySeven() { return finishOnKey('7'); }
	public Gather<E> finishOnKeyEight() { return finishOnKey('8'); }
	public Gather<E> finishOnKeyNine() { return finishOnKey('9'); }

	public Gather<E> numDigits(int numDigits) {
		this.numDigits = numDigits;
		return this;
	}

	public NestInGather[] getNestedVerbs() {
		return nestedVerbs;
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
	public Integer getNumDigits() {
		return numDigits;
	}

}
