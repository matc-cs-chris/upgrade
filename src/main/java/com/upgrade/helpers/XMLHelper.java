package com.upgrade.helpers;

import com.upgrade.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;

public class XMLHelper {
    public static Node getConfigRoot() {
        URL configURL = Main.class.getResource("config.xml");
        File configFile = new File(configURL.getPath());

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(configFile);
            return doc.getDocumentElement();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
