package com.densmolov.services;

import com.densmolov.dao.CatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    public void uploadData(String inputTitle, String inputArtist, String inputCountry,
                           String inputCompany, String inputPrice, String inputYear) {
        if (validate(inputTitle, inputArtist, inputCountry,
                inputCompany, inputPrice, inputYear)) {
            catalogDao.uploadData(inputTitle, inputArtist, inputCountry,
                    inputCompany, inputPrice, inputYear);
        }
    }

    @Override
    public String showData(int pageNumber) {
        return catalogDao.showData(pageNumber);
    }

    private boolean validate(String inputTitle, String inputArtist, String inputCountry,
                            String inputCompany, String inputPrice, String inputYear) {
        return inputTitle != null && inputTitle.length() != 0
                && inputArtist != null && inputArtist.length() != 0
                && inputCountry != null && inputCountry.length() != 0
                && inputCompany != null && inputCompany.length() != 0
                && inputPrice != null && inputPrice.length() != 0
                && inputYear != null && inputYear.length() != 0;
    }

}