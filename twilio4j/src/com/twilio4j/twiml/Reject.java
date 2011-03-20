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


public class Reject extends TwiML {

	public enum Reason {
		rejected, busy
	}
	
	private Reason reason;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Reject");
		if ( reason != null ) { buf.append(" reason=\"").append(reason.name()).append("\""); }
		buf.append("/>\n");
	}
	
	public Reject reason(Reason reason) {
		this.reason = reason;
		return this;
	}
	public Reject reasonBusy() { return reason(Reason.busy); }
	public Reject reasonRejected() { return reason(Reason.rejected); }

	public Reason getReason() {
		return reason;
	}

}