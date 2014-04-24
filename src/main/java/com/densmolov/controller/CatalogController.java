package com.densmolov.controller;

import com.densmolov.services.CatalogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
public class CatalogController {

    private final String FILEPATH = "D:/catalog.xml";

    private final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "forward:/pages/index.html";
    }

    @RequestMapping(value = "/createDisc", method = RequestMethod.POST)
    protected void createCdDisc(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        String inputTitle = request.getParameter("inputTitle");
        String inputArtist = request.getParameter("inputArtist");
        String inputCountry = request.getParameter("inputCountry");
        String inputCompany = request.getParameter("inputCompany");
        String inputPrice = request.getParameter("inputPrice");
        String inputYear = request.getParameter("inputYear");
        catalogService.uploadData(inputTitle, inputArtist, inputCountry,
                inputCompany, inputPrice, inputYear);
    }

    @RequestMapping(value = "/showData", method = RequestMethod.GET,
            produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String showData(@RequestParam int pageNumber) {
        return catalogService.showData(pageNumber);
    }

    @RequestMapping(value = "/downloadCatalog", method = RequestMethod.GET, produces = "text/xml")
    @ResponseBody
    public Response downloadFile(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"catalog.xml\"");
        File file = new File(FILEPATH);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            int fileLength = fileInputStream.available();
            byte[] outputByte = new byte[fileLength];
            while (fileInputStream.read(outputByte) != -1) {
                outputStream.write(outputByte);
            }
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
            logger.info("The file 'catalog.xml' was downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}