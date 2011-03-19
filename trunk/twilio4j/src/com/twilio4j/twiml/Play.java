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
