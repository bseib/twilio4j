package com.twilio4j.twiml;

public class Number<E extends Enum<?>> extends TwiML implements NestInDial {

	private String number;
	private String sendDigits; // TODO these two string *could* have user input that needs escaped so that it doesn't break the twiml parsing.
	private E url;
	
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
