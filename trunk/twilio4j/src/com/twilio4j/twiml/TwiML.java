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
