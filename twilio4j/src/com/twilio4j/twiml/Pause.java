package com.twilio4j.twiml;


public class Pause extends TwiML implements NestInGather {

	private Integer length;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Pause");
		if ( length != null ) { buf.append(" length=\"").append(length.toString()).append("\""); }
		buf.append("/>");
	}
	
	public Pause length(int seconds) {
		this.length = seconds;
		return this;
	}

	public Integer getLength() {
		return length;
	}

}
