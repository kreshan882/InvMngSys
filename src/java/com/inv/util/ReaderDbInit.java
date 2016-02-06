package com.inv.util;




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
public class ReaderDbInit {
	

        
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
    
  
	
}
