package client;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Author;
import model.Book;
import services.XMLservice;
import services.XMLserviceImpl;

public class XMLApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to application");

		Set<Book> books = readXML();
		XMLservice xmlservice = new XMLserviceImpl();

//		try {
//			xmlservice.storeXML(books);
//			System.out.println("Json data stored to the database");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}

	}

	private static Set<Book> readXML() {
		Set<Author> authors = new HashSet<Author>();
		Set<Book> books = new HashSet<Book>();
		Map<String, String> authorMap = new HashMap<>();
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("D:/Script Workspace/XMLJSONProject/xmlbooks.xml"));

			doc.getDocumentElement().normalize();

			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfPersons = doc.getElementsByTagName("book");

			for (int s = 0; s < listOfPersons.getLength(); s++) {

				Node firstPersonNode = listOfPersons.item(s);

				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstPersonNode;

					NodeList idList = firstPersonElement.getElementsByTagName("id");
					Element idElement = (Element) idList.item(0);

					NodeList textIDList = idElement.getChildNodes();
					int id = Integer.parseInt(((Node) textIDList.item(0)).getNodeValue().trim());

					NodeList titleList = firstPersonElement.getElementsByTagName("title");
					Element titleElement = (Element) titleList.item(0);

					NodeList textTitleList = titleElement.getChildNodes();
					String title = ((Node) textTitleList.item(0)).getNodeValue().trim();

					NodeList priceList = firstPersonElement.getElementsByTagName("price");
					Element priceElement = (Element) priceList.item(0);

					NodeList textPriceList = priceElement.getChildNodes();
					int price = Integer.parseInt(((Node) textPriceList.item(0)).getNodeValue().trim());

					NodeList listofAuthors = doc.getElementsByTagName("author");

					for (int i = 0; i < listofAuthors.getLength(); i++) {

						Node firstAuthorNode = listofAuthors.item(i);

						if (firstAuthorNode.getNodeType() == Node.ELEMENT_NODE) {

							Element firstAuthorElement = (Element) firstAuthorNode;

							NodeList authidList = firstAuthorElement.getElementsByTagName("authorid");
							Element authidElement = (Element) authidList.item(0);

							NodeList textAuthidList = authidElement.getChildNodes();
							int authid = Integer.parseInt(((Node) textAuthidList.item(0)).getNodeValue().trim());

							NodeList nameList = firstAuthorElement.getElementsByTagName("name");
							Element nameElement = (Element) nameList.item(0);

							NodeList textNameList = nameElement.getChildNodes();
							String name = ((Node) textNameList.item(0)).getNodeValue().trim();
							Author aObj = new Author();
							aObj.setId(authid);
							aObj.setName(name);

							authors.add(aObj);
							System.out.println(authors.toString());
							System.out.println("=====================================");

						}

					}
					Book bObj = new Book();
					bObj.setId(id);
					bObj.setTitle(title);
					bObj.setPrice(price);
					bObj.setAuthors(authors);
					books.add(bObj);
					System.out.println(books);
					System.out.println("=======================================");
				}

			}
			System.out.println("Data is successfully inserted!");
			System.out.println(books.toString());
			System.out.println("=============================================");
		}

		catch (Exception err) {
			System.out.println(" " + err.getMessage());
		}
		return books;
	}
}
