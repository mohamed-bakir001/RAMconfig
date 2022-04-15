package com.ram.config2;

import com.ram.config2.entity.Airplane;
import com.ram.config2.entity.LoadableSW;
import com.ram.config2.entity.Swlocation;
import com.ram.config2.entity.Systeme;
import com.ram.config2.repository.RepoAirplan;
import com.ram.config2.repository.RepoLoadableSW;
import com.ram.config2.repository.RepoSwlocation;
import com.ram.config2.repository.RepoSystem;
import com.ram.config2.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication


public class Config2Application implements CommandLineRunner {

	/*private RepoAirplan repoAirplan;
	private RepoSystem repoSystem ;
	private RepoSwlocation repoSwlocation ;
	private RepoLoadableSW repoLoadableSW ;

	@Autowired
	Config2Application(RepoLoadableSW repoLoadableSW , RepoAirplan repoAirplan ,
					   RepoSwlocation repoSwlocation , RepoSystem repoSystem){
		this.repoAirplan = repoAirplan ;
		this.repoSwlocation = repoSwlocation ;
		this.repoSystem = repoSystem ;
		this.repoLoadableSW = repoLoadableSW ;
	}
	*/

	private RepoAirplan repoAirplan;
	private XmlService xmlService ;

	@Autowired
	Config2Application(RepoAirplan repoAirplan , XmlService xmlService){
		this.repoAirplan = repoAirplan ;
		this.xmlService = xmlService ;
	}

	public static void main(String[] args) {
		SpringApplication.run(Config2Application.class, args);
	}


	@Override
	public void run(String... arg0) throws Exception {

		xmlService.xmlParser("files/file.xml");
		List<Airplane> airplanes = repoAirplan.findAll();
		for(int i= 0 ; i< airplanes.size() ; i++){
			System.out.println(airplanes.get(i).getTailNumber());
		}
		/*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File( "files/file.xml" ));
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();

		//extract Airplane Object
		Node airplaneNode = root.getFirstChild();
		Airplane airplane = new Airplane() ;
		airplane.setTailNumber(airplaneNode.getAttributes().getNamedItem("TailNumber").getNodeValue());
		String date = root.getAttribute("Date") + " " + root.getAttribute("Time").substring(0,8);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		airplane.setDate(formatter.parse(date));

		System.out.println(airplane);

		//Save airplane Object
		Airplane airplane1 = repoAirplan.save(airplane);

		//extract System objects
		NodeList nList = document.getElementsByTagName("System");
		System.out.println("----------------------------");

		for (int item = 0 ; item<nList.getLength() ; item++) {
			Node node = nList.item(item) ;
			//extract System object
			Systeme systeme = new Systeme();
			systeme.setName(node.getAttributes().getNamedItem("Name").getNodeValue());
			systeme.setAirplane(airplane1);

			System.out.println(systeme.toString());
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
					System.out.println(swlocation.toString());

					//save SWlocateion
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
							System.out.println(loadableSW.toString());

							//save LoadableSw
							repoLoadableSW.save(loadableSW);


						}

					}

				}


			}


			System.out.println("***************************************");
		}*/
	}

}
