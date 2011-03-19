package com.twilio4j.twiml;


public class Hangup extends TwiML {

	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Hangup/>");
	}

}
