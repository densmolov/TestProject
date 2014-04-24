package com.densmolov.dao;

import com.densmolov.entity.XmlProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogDaoImpl implements CatalogDao {

    private final Logger logger = LoggerFactory.getLogger(CatalogDaoImpl.class);

    @Override
    public void uploadData(String inputTitle, String inputArtist, String inputCountry,
                           String inputCompany, String inputPrice, String inputYear) {
        XmlProcessor xmlProcessor = new XmlProcessor();
        xmlProcessor.modifyXml(inputTitle, inputArtist, inputCountry,
                inputCompany, inputPrice, inputYear);
    }

    @Override
    public String showData(int pageNumber) {
        XmlProcessor xmlProcessor = new XmlProcessor();
        return xmlProcessor.showData(pageNumber);
    }

}