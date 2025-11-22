package com.upgrade.helpers;

import com.upgrade.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

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

    public static Node getChild(Node node, String name) {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodes.item(i).getNodeName().equals(name)) {
                    return nodes.item(i);
                }
            }
        }

        return null;
    }

    public static LinkedList<Node> getChildren(Node node, String name) {
        LinkedList<Node> childNodes = new LinkedList<>();

        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodes.item(i).getNodeName().equals(name)) {
                    childNodes.add(nodes.item(i));
                }
            }
        }

        return childNodes;
    }
}
