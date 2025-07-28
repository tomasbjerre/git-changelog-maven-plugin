package se.bjurr.gitchangelog.plugin;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlModifier {

  private final File file;

  public XmlModifier(final File file) {
    this.file = file;
  }

  public void setVersion(final String value) throws Exception {
    final Document document = this.readDocument();

    final Node projectNode = this.getNode(document.getChildNodes(), "project");

    // Not multimodule with version in parent pom
    final Node versionNodeOpt = this.findNode(projectNode.getChildNodes(), "version");
    if (versionNodeOpt != null) {
      versionNodeOpt.setTextContent(value);
    } else {
      // Probably version in parent/version node
      final Node parentNode = this.getNode(projectNode.getChildNodes(), "parent");
      final Node versionNode = this.getNode(parentNode.getChildNodes(), "version");
      versionNode.setTextContent(value);
    }

    this.saveDocument(document);
  }

  private Document readDocument() throws ParserConfigurationException, SAXException, IOException {
    final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    final Document document = dBuilder.parse(this.file);
    return document;
  }

  private void saveDocument(final Node document) throws Exception {
    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    final Transformer transformer = transformerFactory.newTransformer();
    final DOMSource source = new DOMSource(document);
    final StreamResult result = new StreamResult(this.file);
    transformer.transform(source, result);
  }

  private Node findNode(final NodeList nodes, final String tagName) {
    Node found = null;
    for (int tagIndex = 0; tagIndex < nodes.getLength(); tagIndex++) {
      final Node tag = nodes.item(tagIndex);
      if (tag.getNodeName().equals(tagName)) {
        if (found != null) {
          throw new RuntimeException("Found multiple " + tagName + " in " + nodes);
        }
        found = tag;
      }
    }
    return found;
  }

  private Node getNode(final NodeList nodes, final String tagName) {
    Node found = findNode(nodes, tagName);
    if (found == null) {
      throw new RuntimeException("Cannot find " + tagName + " in " + nodes);
    }
    return found;
  }
}
