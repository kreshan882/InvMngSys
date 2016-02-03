package com.inv.xml;




import com.inv.init.InitConfigValue;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jpos.core.Configuration;
import org.jpos.core.SimpleConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * Title       : InitXmlReader
 * Description : Read xml file to load initial configuration values.
 * Company     : Epic Lanka (pvt) Ltd
 * 
 * @author sadun 
 *
 */
public class InitXmlReader {
	
	private static NodeList nList = null;
	private static NodeList nList1 = null;
	private static Element elem = null;
	/**
	 * this method read the values of serverconf.xml
	 * @throws Exception
	 */
        
        public static void readConfigValues() throws Exception {
            Configuration cfg = new SimpleConfiguration(InitConfigValue.SCONFIGPATH+"dbInit.int");
            InitConfigValue.DBUSERNAME                          = cfg.get("DB_USERNAME").trim();
            InitConfigValue.DBPASSWORD                          = cfg.get("DB_PASSWORD").trim();
            InitConfigValue.DBDRIVER                            = cfg.get("DB_DRIVER").trim();
            InitConfigValue.DBURL                               = cfg.get("DB_URL").trim();
            InitConfigValue.MINPOOL                             = Integer.parseInt(cfg.get("MIN_POOL").trim());
            InitConfigValue.MAXPOOL                             = Integer.parseInt(cfg.get("MAX_POOL").trim());
            InitConfigValue.MAXCON                              = Integer.parseInt(cfg.get("MAX_CON").trim());

            InitConfigValue.DBCONNECTIONTIMEOUT                 = Integer.parseInt(cfg.get("DB_CONNECTION_TIMEOUT").trim());
            InitConfigValue.DBCONEXPIRTIMEOUT                   = Integer.parseInt(cfg.get("DB_CON_EXPIR_TIMEOUT").trim());
        }
    
    
//	public static void readConfigValues() throws Exception {
//
//		
//		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//		Document doc = docBuilder.parse(new File(InitConfigValue.SCONFIGPATH+"e24ocmConfig.xml"));
//		doc.getDocumentElement().normalize();
//		NodeList listOfSPRMs = doc.getElementsByTagName("SCONFIG");
//		Node firstSPRMNode = listOfSPRMs.item(0);
//
//		if (firstSPRMNode.getNodeType() == Node.ELEMENT_NODE) {
//			
//			Element firstSPRAMElement = (Element) firstSPRMNode;
//
//			
//			// Getting db username  
//			nList = firstSPRAMElement.getElementsByTagName("DBUSERNAME");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBUSERNAME= ((Node) nList1.item(0)).getNodeValue().trim();
//			doEmpty();	
//		
//			
//		
//			
//			// Getting db password
//			nList = firstSPRAMElement.getElementsByTagName("DBPASSWORD");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBPASSWORD= ((Node) nList1.item(0)).getNodeValue().trim();
//			doEmpty();	
//			
//			
//			// Getting db driver
//			nList = firstSPRAMElement.getElementsByTagName("DBDRIVER");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBDRIVER= ((Node) nList1.item(0)).getNodeValue().trim();
//			doEmpty();
//			
//			
//			// Getting db url
//			nList = firstSPRAMElement.getElementsByTagName("DBURL");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBURL= ((Node) nList1.item(0)).getNodeValue().trim();
//			doEmpty();
//			
//			
//			
//			
//			// Getting db max con size
//			nList = firstSPRAMElement.getElementsByTagName("DBMAXCON");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.MAXCON= Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
//			doEmpty();
//			
//			// Getting db min pool size
//			nList = firstSPRAMElement.getElementsByTagName("DBMINPOOL");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.MINPOOL= Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
//			doEmpty();
//			
//			// Getting db max pool size
//			nList = firstSPRAMElement.getElementsByTagName("DBMAXPOOL");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.MAXPOOL= Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
//			doEmpty();
//			
//			
//			// Getting db connection timeout 
//			nList = firstSPRAMElement.getElementsByTagName("DBCONNECTIONTIMEOUT");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBCONNECTIONTIMEOUT= Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
//			doEmpty();
//			
//			
//		
//			// Getting db expir timeout
//			nList = firstSPRAMElement.getElementsByTagName("DBEXPIRTIMEOUT");
//			elem = (Element) nList.item(0);
//			nList1 = elem.getChildNodes();
//			InitConfigValue.DBCONEXPIRTIMEOUT= Integer.parseInt(((Node) nList1.item(0)).getNodeValue().trim());
//			doEmpty();
//			
//	
//		
//		
//	
//		
//
//			
//		}
//		
//	}
//	
//
//	private static void doEmpty() {
//		nList1 = null;
//		nList = null;
//		elem = null;
//	}

	
	
	
	
}
