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

/**
 * This class directly reflects the Number verb documented at
 * http://www.twilio.com/docs/api/twiml/number
 * 
 * All of the descriptions included in these javadoc comments
 * come directly from the twilio website.
 * 
 * @author broc.seib@gentomi.com
 */
public class Number<E extends Enum<?>> extends TwiML implements NestInDial {

	private String number;
	private String sendDigits; // TODO these two string *could* have user input that needs escaped so that it doesn't break the twiml parsing.
	private E url;
	
	/**
	 * Converts this object into XML. This function is normally called by the state
	 * machine servlet and not called directly by you.
	 */
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Number");
		if ( sendDigits != null ) { buf.append(" sendDigits=\"").append(sendDigits).append("\""); }
		if ( url != null ) { buf.append(" url=\"").append(baseUrl).append(url.name()).append("\""); }
		buf.append('>');
		buf.append(number);
		buf.append("</Number>");
	}
	
	public Number(String number) {
		this.number = number;
	}
	
	public Number<E> sendDigits(String sendDigits) {
		this.sendDigits = sendDigits;
		return this;
	}
	
	public Number<E> url(E url) {
		this.url = url;
		return this;
	}

	public String getNumber() {
		return number;
	}
	public String getSendDigits() {
		return sendDigits;
	}
	public E getUrl() {
		return url;
	}

}
