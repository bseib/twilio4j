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
