package com.twilio4j.twiml;

public class Say extends TwiML implements NestInGather {
	
	public enum Voice {
		man, woman
	};
	public enum Language {
		en, es, fr, de
	}

	private String phrase;
	private Voice voice;
	private Language language;
	private Integer loop;
	
	@Override
	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Say");
		if ( voice != null ) { buf.append(" voice=\"").append(voice.name()).append("\""); }
		if ( language != null ) { buf.append(" language=\"").append(language.name()).append("\""); }
		if ( loop != null ) { buf.append(" loop=\"").append(loop.toString()).append("\""); }
		buf.append('>');
		buf.append(escape(phrase));
		buf.append("</Say>");
	}
	
	public Say(String phrase) {
		this.phrase = phrase;
	}
	
	public Say voice(Voice v) {
		this.voice = v;
		return this;
	}
	public Say voiceMAN() { return voice(Voice.man); }
	public Say voiceWOMAN() { return voice(Voice.woman); }
	
	public Say language(Language l) {
		this.language = l;
		return this;
	}
	public Say languageEN() { return language(Language.en); }
	public Say languageES() { return language(Language.es); }
	public Say languageFR() { return language(Language.fr); }
	public Say languageDE() { return language(Language.de); }
	
	public Say loop(int loopCount) {
		this.loop = loopCount;
		return this;
	}

	public String getPhrase() {
		return phrase;
	}
	public Voice getVoice() {
		return voice;
	}
	public Language getLanguage() {
		return language;
	}
	public Integer getLoop() {
		return loop;
	}

}
