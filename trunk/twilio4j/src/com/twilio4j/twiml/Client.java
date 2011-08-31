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
 * This class directly reflects the Conference verb documented at
 * <a href="http://www.twilio.com/docs/api/twiml/client">http://www.twilio.com/docs/api/twiml/client</a>
 * 
 * All of the descriptions included in these javadoc comments
 * come directly from the twilio website.
 * 
 * @author broc.seib@gentomi.com
 */
public class Client<E extends Enum<?>> extends TwiML implements NestInDial {

	private String clientIdentifier;
	
	/**
	 * Converts this object into XML. This function is normally called by the state
	 * machine servlet and not called directly by you.
	 */
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Client>");
		buf.append(escape(clientIdentifier));
		buf.append("</Client>");
	}

	/**
	 * <p>The {@link Dial} verb's {@link Client} noun specifies a client identifier to dial.</p>
	 * 
	 * <p>You can use multiple {@link Client} nouns within a {@link Dial} verb to simultaneously
	 * attempt a connection with many clients at once. The first client to accept the incoming
	 * connection is connected to the call and the other connection attempts are canceled. If
	 * you want to connect with multiple other clients simultaneously, read about the
	 * {@link Conference} noun.</p>
	 * 
	 * <p>Note: The client identifier currently may only contain alpha-numeric and underscore
	 * characters.</p>
	 * 
	 * @param clientIdentifier  The identifier for the client.
	 */
	public Client(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}
	
	public String getClientIdentifier() {
		return clientIdentifier;
	}

}
