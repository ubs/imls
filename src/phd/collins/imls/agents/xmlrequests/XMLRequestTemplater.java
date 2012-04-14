package phd.collins.imls.agents.xmlrequests;

public class XMLRequestTemplater {
	
	public static String getAuthenticateRequestXML(String strServiceName, String strUsername, String strPassword){
		String strAuthRequestXML = "";
		String strAuthRequestParameters = new StringBuffer()
		.append("<urn:Authenticate soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">")
		.append("<username xsi:type=\"xsd:string\">").append(strUsername).append("</username>")
		.append("<password xsi:type=\"xsd:string\">").append(strPassword).append("</password>")
		.append("</urn:Authenticate>").toString();
		
		strAuthRequestXML = prepareXMLSoapRequest(strServiceName, strAuthRequestParameters);
		return strAuthRequestXML;
	}
	
	public static String getStudyAreaRequestXML(String strServiceName, String strUsername, String strPassword){
		String strRequestXML = "";
		String strRequestParameters = new StringBuffer()
			.append("<urn:Authenticate soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">")
			.append("<username xsi:type=\"xsd:string\">").append(strUsername).append("</username>")
			.append("<password xsi:type=\"xsd:string\">").append(strPassword).append("</password>")
			.append("</urn:Authenticate>").toString();
		
		strRequestXML = prepareXMLSoapRequest(strServiceName, strRequestParameters);
		return strRequestXML;
	}

	private static String prepareXMLSoapRequest(String strServiceName, String strSoapRequestParameters) {
		String strSoapRequestXML;
		strSoapRequestXML = getXMLSoapHeaderTag() + getXMLSoapBodyTag(strSoapRequestParameters);
		strSoapRequestXML = getXMLSoapEnvelopeTag(strServiceName, strSoapRequestXML);
		return strSoapRequestXML;
	}
	
	public static String getXMLSoapHeaderTag(){
		StringBuffer headerTag = new StringBuffer().append("<soapenv:Header/>");
		return headerTag.toString();
	}
	
	public static String getXMLSoapBodyTag(String strBodyContent){
		StringBuffer bodyTag = new StringBuffer()
		.append("<soapenv:Body>").append(strBodyContent).append("</soapenv:Body>");

		return bodyTag.toString();
	}
	
	public static String getXMLSoapEnvelopeTag(String strServiceName, String strContent){
		StringBuffer envelopeTag = new StringBuffer() 
			.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
			.append("<soapenv:Envelope ")
			.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ")
			.append("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ")
			.append("xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ")
			.append("xmlns:urn=\"urn:").append(strServiceName).append("\">")
			.append(strContent)
			.append("</soapenv:Envelope>");
		
		return envelopeTag.toString();
	}
		
}
