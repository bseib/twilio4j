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
