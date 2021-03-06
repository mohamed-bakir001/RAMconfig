package com.ram.config2.service;

import com.ram.config2.entity.Airplane;
import com.ram.config2.entity.LoadableSW;
import com.ram.config2.entity.Swlocation;
import com.ram.config2.entity.Systeme;
import com.ram.config2.exception.AirplanNotFoundException;
import com.ram.config2.repository.RepoAirplan;
import com.ram.config2.repository.RepoLoadableSW;
import com.ram.config2.repository.RepoSwlocation;
import com.ram.config2.repository.RepoSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
public class XmlService {

    //private Path foundFile ;
    private RepoAirplan repoAirplan;
    private RepoSystem repoSystem ;
    private RepoSwlocation repoSwlocation ;
    private RepoLoadableSW repoLoadableSW ;
    private DataManageService dataManageService ;


    @Autowired
    XmlService(RepoLoadableSW repoLoadableSW ,
               RepoAirplan repoAirplan ,
               RepoSwlocation repoSwlocation ,
               RepoSystem repoSystem,
               DataManageService dataManageService){
        this.repoAirplan = repoAirplan ;
        this.repoSwlocation = repoSwlocation ;
        this.repoSystem = repoSystem ;
        this.repoLoadableSW = repoLoadableSW ;
        this.dataManageService = dataManageService ;
    }

    public XmlService(){}


    public boolean xmlParser(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        //Initiation of document xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();

        //Extract Airplane Object
        Node airplaneNode = root.getFirstChild();
        Airplane airplane = new Airplane() ;
        airplane.setTailNumber(airplaneNode.getAttributes().getNamedItem("TailNumber").getNodeValue());
        String date = root.getAttribute("Date") + " " + root.getAttribute("Time").substring(0,8);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        airplane.setDate(formatter.parse(date));

        //System.out.println(airplane);
        try{
            Airplane air = repoAirplan.findAirplaneByTailNumber(airplane.getTailNumber());
            dataManageService.deleteDataAirplan(air.getAirpId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        //Save airplane Object
        Airplane airplane1 = repoAirplan.save(airplane);

        if(airplane1 != null ){
            //Extract System objects
            NodeList nList = document.getElementsByTagName("System");
            //System.out.println("----------------------------");


            for (int item = 0 ; item<nList.getLength() ; item++) {
                Node node = nList.item(item) ;
                //Extract System object
                Systeme systeme = new Systeme();
                systeme.setName(node.getAttributes().getNamedItem("Name").getNodeValue());
                systeme.setAirplane(airplane1);

                //System.out.println(systeme.toString());
                //Save Systeme
                Systeme systeme1 = repoSystem.save(systeme);



                NodeList swLocationNode = node.getChildNodes();
                for (int it = 0 ; it <swLocationNode.getLength(); it++ ){
                    Node nodeSW = swLocationNode.item(it);
                    //System.out.println(nodeSW.getNodeName());
                    //Extract SWlocation object
                    if(nodeSW.getNodeName()== "SWLocation"){
                        Swlocation swlocation = new Swlocation();
                        swlocation.setDescription(nodeSW.getAttributes().getNamedItem("Description").getNodeValue());
                        swlocation.setType(nodeSW.getAttributes().getNamedItem("Type").getNodeValue());
                        swlocation.setValue(nodeSW.getAttributes().getNamedItem("Value").getNodeValue());
                        swlocation.setSysteme(systeme1);
                        //System.out.println(swlocation.toString());

                        //Save SWlocateion
                        Swlocation swlocation1 = repoSwlocation.save(swlocation);
                        NodeList loadableNode = nodeSW.getChildNodes();
                        for (int itm = 0 ; itm < loadableNode.getLength() ; itm++){
                            //Extract Loadable Object
                            Node nodeload = loadableNode.item(itm);
                            //System.out.println(nodeload.getNodeName());
                            if(nodeload.getNodeName()=="LoadableSW"){
                                LoadableSW loadableSW = new LoadableSW();
                                loadableSW.setDescription(nodeload.getAttributes().getNamedItem("Description").getNodeValue());
                                loadableSW.setPartNumber(nodeload.getAttributes().getNamedItem("PartNumber").getNodeValue());
                                loadableSW.setSwlocation(swlocation1);
                                //System.out.println(loadableSW.toString());
                                //Save LoadableSw
                                repoLoadableSW.save(loadableSW);
                            }
                        }
                    }
                }
                //System.out.println("***************************************");
            }
            return true ;
        }else{
            return false ;
        }
    }
}
