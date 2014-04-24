package com.densmolov.services;


public interface CatalogService {

    public void uploadData(String inputTitle, String inputArtist, String inputCountry,
                           String inputCompany, String inputPrice, String inputYear);

    public String showData(int pageNumber);

}