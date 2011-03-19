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


public class TwiML implements ToXML {
	
	private TwiML[] nested;
	
	public TwiML(TwiML... nested) {
		this.nested = nested;
	}
	
	public TwiML[] getNested() {
		return nested;
	}

	public String toXml(String baseUrl) {
		StringBuilder buf = new StringBuilder();
		toXml(buf, baseUrl);
		return buf.toString();
	}

	public void toXml(StringBuilder buf, String baseUrl) {
		buf.append("<Response>");
		for ( TwiML t : nested ) {
			t.toXml(buf, baseUrl);
		}
		buf.append("</Response>");
	}
	
	protected String escape(String val) {
		val = val.replaceAll("&", "&amp;");
		val = val.replaceAll(">", "&gt;");
		val = val.replaceAll("<", "&lt;");
		return val;
	}
	
//	public String toXML(){
//        String xml = "<" + this.tag;
//        for (String key : attributes.keySet()) {
//            xml += " " + key + "=\"" + attributes.get(key) + "\"";
//        }
//        xml += ">";
//        if(this.body != null)
//            xml += this.body;
//        for (Verb child : children){
//            xml += child.toXML();
//        }
//        return xml += "</" + this.tag + ">";
//    }

}
