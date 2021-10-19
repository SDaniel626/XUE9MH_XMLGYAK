package SaxXUE9MH1019;

public class SaxHandler extends DefaultHandler {
	private int indent = 0;
	
	private String formatAttributes(Attributes attributes) {
		int attrLength = attributes.getLength();
		if(attrLength == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(", {");
		for(int i = 0; i < attrLength; i++) {
			sb.append(attributes.getLocalName(i) + ":" + attributes.getValue(i));
			if( i < attrLength -1) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	private void indent() {
		for(int i = 0; i < indent; i++) {
			System.out.println(" ");
		}
	}
	
	@Override
	public void startElement(String uri, String LocalName, String qName, Attributes attributes) {
		indent++;
		indent();
		System.out.println(qName + FormatAttributes(attributes) + " start");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		indent();
		indent--;
		System.out.println(qName + " end");
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch, start, length).trim();
		if(!chars.isEmpty()) {
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}
}
