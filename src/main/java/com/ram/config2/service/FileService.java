package com.ram.config2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

@Service
public class FileService {
    private XmlService xmlService;

    @Autowired
    FileService(XmlService xmlService) {
        this.xmlService = xmlService ;
    }


    public boolean  parseXmlFile(MultipartFile multipartFile) throws IOException, ParserConfigurationException, ParseException, SAXException {
        InputStream inputStream = multipartFile.getInputStream();
        if(xmlService.xmlParser(inputStream)){
            return true ;
        }else {
            return false ;
        }
    }
}
