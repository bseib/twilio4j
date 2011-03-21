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
 * This class directly reflects the Redirect verb documented at
 * http://www.twilio.com/docs/api/twiml/redirect
 * 
 * All of the descriptions included in these javadoc comments
 * come directly from the twilio website.
 * 
 * @author broc.seib@gentomi.com
 */
public class Redirect<E extends Enum<?>> extends TwiML {

	private E nextState;
	private Method method;
	
	/**
	 * Converts this object into XML. This function is normally called by the state
	 * machine servlet and not called directly by you.
	 */
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
