package phd.collins.imls.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import phd.collins.imls.util.Info;
import phd.collins.imls.util.UtilGeneral;

public class XML2Hash {
	private String xmlString;
	private Document parsedXMLDoc;
	private DocumentBuilder docBuilder;
	private DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	private Hashtable<String, String> xmlHashTable = new Hashtable<String, String>();
	
	public XML2Hash() {
		super();
	}
	
	public XML2Hash(String strXML) {
		this();
		parseXMLString(strXML);
		toHashTable();
	}
	
	public XML2Hash(String strXML, boolean toHashTable) {
		this();
		parseXMLString(strXML);
		if (toHashTable){ toHashTable(); }
	}
	
	public void parseXMLString(String strXML){
		Info.sout("Me is Parsing XML from " + strXML);
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			setParsedXMLDoc( docBuilder.parse(new InputSource(new StringReader(strXML))) );
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	public String getXmlString() {
		return xmlString;
	}
	
	public void setParsedXMLDoc(Document parsedXMLDoc) {
		this.parsedXMLDoc = parsedXMLDoc;
	}
	
	public Hashtable<String, String> getXmlHashTable() {
		return xmlHashTable;
	}

	public Document getParsedXMLDoc() {
		return parsedXMLDoc;
	}
	
	public void toHashTable(){
		toHashTable(getParsedXMLDoc());
	}
	
	public void toHashTable(Document xmlDoc){
		NodeList docNodes = xmlDoc.getChildNodes(); //.item(0).getChildNodes();
        harvestXMLElementsIntoHash(docNodes, xmlHashTable);
	}
	
	private void harvestXMLElementsIntoHash(NodeList docNodes, Hashtable<String, String> hashTable){
		for(int docNodesIndex = 0; docNodesIndex < docNodes.getLength(); docNodesIndex++ ) { 
 
            if ( docNodes.item(docNodesIndex).getNodeType() == Node.ELEMENT_NODE ) {
            	Element nodeElement = (Element)docNodes.item(docNodesIndex);
            	//Info.sout("nodeElement.getNodeName():" + nodeElement.getNodeName());
            	//Info.sout("nodeElement.getTextContent(): " + nodeElement.getTextContent());
            	//Info.sout("nodeElement.getNodeType(): " + nodeElement.getNodeType());

            	if (nodeElement != null){
            		hashTable.put( String.valueOf(nodeElement.getNodeName()), String.valueOf(nodeElement.getTextContent()) );
            		
            		if (nodeElement.hasChildNodes()) harvestXMLElementsIntoHash(nodeElement.getChildNodes(), hashTable);
            	}
            }
        }
	}
	
	public void dumpHashTable(){
		Info.sout("DUMPING XML Hash Table, Size: " + xmlHashTable.size());
		
		Enumeration<String> hashKeys = xmlHashTable.keys();
		while( hashKeys.hasMoreElements() ) {
		  String hKey = hashKeys.nextElement();
		  String hValue = xmlHashTable.get(hKey);
		  Info.sout("Key[Value] : " + hKey + "[" + hValue + "]");
		}
	}
	
	public static Hashtable<String, String> XML2HashTable(String strXML) {
		return new XML2Hash(strXML).getXmlHashTable();
	}
	
	//Main Test Method
	public static void main(String[] args) throws Exception {
		String strXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><AuthenticateResponse xmlns=\"urn:IMLS_Authentication_Agent28\"><AuthenticateReturn xmlns=\"\"><authenticated xmlns=\"\">false</authenticated><isActive xmlns=\"\">false</isActive><lastLoginDate xmlns=\"\">2012-01-21T21:06:00.750</lastLoginDate><password xmlns=\"\">passworx</password><userType xmlns=\"\">ADMIN</userType><username xmlns=\"\">usernameskySat Jan 21 18:46:48 GMT 2012</username></AuthenticateReturn></AuthenticateResponse></soapenv:Body></soapenv:Envelope>";
		
		String strXML2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<company>" +
				"<employee>" +
					"<firstname>Anusmita</firstname>" +
					"<lastname>Singh</lastname>" +
				"</employee>" +
			"</company>";
		
		XML2Hash xml2Hash = new XML2Hash(strXML);
		xml2Hash.dumpHashTable();
		
		new XML2Hash(strXML2).dumpHashTable();
		
		String strXML3 = "<transactions>" + 
				"<account id=\"a\">" +
					"<transaction amount=\"500\">500</transaction>" +
					"<transaction amount=\"1200\">1200</transaction>" +
				"</account>" +
				"<account id=\"b\">" + 
					"<transaction amount=\"600\">600</transaction>" + 
					"<transaction amount=\"800\">800</transaction>" + 
					"<transaction amount=\"2000\">2000</transaction>" + 
				"</account>" + 
			"</transactions>";
		
		UtilGeneral.dumpHashTable(XML2Hash.XML2HashTable(strXML3));
	}

}
