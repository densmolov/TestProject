package com.densmolov.entity;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class XmlProcessor {

    private static Logger logger = LoggerFactory.getLogger(XmlProcessor.class);

    private final String FILEPATH = "D:/catalog.xml";

    public void modifyXml(String cdTitle, String cdArtist, String cdCountry,
                          String cdCompany, String cdPrice, String cdYear) {
        boolean alreadyExists = false;
        SAXBuilder builder = new SAXBuilder();
        File file = new File(FILEPATH);
        try {
            Document document = builder.build(file);
            Element catalog = document.getRootElement();
            List cdList = catalog.getChildren("CD");
            for (Object aCdList : cdList) {
                Element everyCD = (Element) aCdList;
                Element title = everyCD.getChild("TITLE");
                if (title.getTextNormalize().equalsIgnoreCase(cdTitle)) {
                    everyCD.getChild("ARTIST").setText(cdArtist);
                    everyCD.getChild("COUNTRY").setText(cdCountry);
                    everyCD.getChild("COMPANY").setText(cdCompany);
                    everyCD.getChild("PRICE").setText(cdPrice);
                    everyCD.getChild("YEAR").setText(cdYear);
                    alreadyExists = true;
                }
            }
            if (!alreadyExists) {
                Element newCdElement = new Element("CD");
                newCdElement.addContent(new Element("TITLE").setText(cdTitle));
                newCdElement.addContent(new Element("ARTIST").setText(cdArtist));
                newCdElement.addContent(new Element("COUNTRY").setText(cdCountry));
                newCdElement.addContent(new Element("COMPANY").setText(cdCompany));
                newCdElement.addContent(new Element("PRICE").setText(cdPrice));
                newCdElement.addContent(new Element("YEAR").setText(cdYear));
                document.getRootElement().addContent(newCdElement);
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(FILEPATH));
        } catch (IOException | JDOMException io) {
            io.printStackTrace();
        }
        logger.info("The data was uploaded successfully : " + cdTitle);
    }

    public String showData(int pageNumber) {
        SAXBuilder builder = new SAXBuilder();
        File file = new File(FILEPATH);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Document document = builder.build(file);
            Element catalog = document.getRootElement();
            List cdList = catalog.getChildren("CD");

            int firstResult = (pageNumber - 1) * 5;
            int interResult = (firstResult + 5);
            if (interResult > (cdList.size() - 1)) {
                interResult = (cdList.size());
            }
            int lastResult = interResult;
            stringBuilder.append("<CATALOG>");
            stringBuilder.append("<PAGES>");
            stringBuilder.append(cdList.size());
            stringBuilder.append("</PAGES>");
            for (int i = firstResult; i < lastResult; i++) {
                Element cd = (Element) cdList.get(i);

                stringBuilder.append("<CD>");
                stringBuilder.append("<TITLE>");
                stringBuilder.append(cd.getChildText("TITLE"));
                stringBuilder.append("</TITLE>");

                stringBuilder.append("<ARTIST>");
                stringBuilder.append(cd.getChildText("ARTIST"));
                stringBuilder.append("</ARTIST>");

                stringBuilder.append("<COUNTRY>");
                stringBuilder.append(cd.getChildText("COUNTRY"));
                stringBuilder.append("</COUNTRY>");

                stringBuilder.append("<COMPANY>");
                stringBuilder.append(cd.getChildText("COMPANY"));
                stringBuilder.append("</COMPANY>");

                stringBuilder.append("<PRICE>");
                stringBuilder.append(cd.getChildText("PRICE"));
                stringBuilder.append("</PRICE>");

                stringBuilder.append("<YEAR>");
                stringBuilder.append(cd.getChildText("YEAR"));
                stringBuilder.append("</YEAR>");
                stringBuilder.append("</CD>");
            }
            stringBuilder.append("</CATALOG>");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        logger.info(stringBuilder.toString());
        return stringBuilder.toString();
    }

}