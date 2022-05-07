package com.ram.config2.controller;

import com.ram.config2.service.FileService;
import com.ram.config2.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.List;

@Controller
public class UploadFileController {

    private FileService fileService;

    @Autowired
    UploadFileController(FileService fileService){
        this.fileService = fileService ;
    }


    @PostMapping("/uploadfile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, ParserConfigurationException, ParseException, SAXException {
        boolean feed = this.fileService.parseXmlFile(multipartFile);
        if(feed == true){
            return new ResponseEntity<>("File parser successfully....", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something is wrong, the file is not deleted", HttpStatus.NOT_FOUND);
        }
    }


}
