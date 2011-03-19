package com.twilio4j.twiml;


public class Play extends TwiML implements NestInGather {

	private String audioUrl;
	private Integer loop;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Play");
		if ( loop != null ) { buf.append(" loop=\"").append(loop.toString()).append("\""); }
		buf.append('>');
		buf.append(escape(audioUrl));
		buf.append("</Play>");
	}
	
	public Play(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	
	public Play loop(int loopCount) {
		this.loop = loopCount;
		return this;
	}

	public String getAudioUrl() {
		return audioUrl;
	}
	public Integer getLoop() {
		return loop;
	}

}
