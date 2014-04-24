package com.densmolov.dao;

public interface CatalogDao {

    public void uploadData(String inputTitle, String inputArtist, String inputCountry,
                           String inputCompany, String inputPrice, String inputYear);

    public String showData(int pageNumber);

}