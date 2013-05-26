package main.load;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import main.HibernateUtil;
import mapping.Customer;
import mapping.Order;
import mapping.OrderDetail;
import mapping.Product;
import mapping.Shipper;
import mapping.Supplier;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class OrdersParser {


	File ordersFile;
	File orderDetailsFile;
	HashMap<Order, HashSet<OrderDetail>> data;
	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	Document ordersDoc, ordersDetailDoc;
	
	
	void setMode(int mode)
	{
		if(mode==1)
		{
			ordersFile = new File(
					"C:\\Users\\Alicja\\studia\\6 semestr\\bazy\\sampledata\\orders_rand_10000.xml");
			orderDetailsFile = new File(
					"C:\\Users\\Alicja\\studia\\6 semestr\\bazy\\sampledata\\orderdetails_rand_10000.xml");
			
		}
		else
		{
			ordersFile = new File(
					"C:\\Users\\Alicja\\studia\\6 semestr\\bazy\\sampledata\\orders_rand_20000.xml");
			orderDetailsFile = new File(
					"C:\\Users\\Alicja\\studia\\6 semestr\\bazy\\sampledata\\orderdetails_rand_20000.xml");
		}
		
		try {

			dBuilder = dbFactory.newDocumentBuilder();
			
			ordersDoc = dBuilder.parse(ordersFile);
			ordersDoc.getDocumentElement().normalize();

			ordersDetailDoc = dBuilder.parse(orderDetailsFile);
			ordersDetailDoc.getDocumentElement().normalize();
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	
	public HashMap<Order,HashSet< OrderDetail>> get1000Orders(int howMany) {
		
		data = new HashMap<Order, HashSet<OrderDetail>>();
		
		NodeList orders = ordersDoc.getElementsByTagName("order");
		NodeList orderDetails = ordersDetailDoc.getElementsByTagName("orderdetail");
		
		int lol=0;
		while(lol%1000!=0 || lol==0 ){
			Node orderNode = orders.item(howMany);

			if (orderNode.getNodeType() == Node.ELEMENT_NODE) {
				Element orderElement = (Element) orderNode;
				
				Order order = new Order(
						Integer.parseInt(getValue("OrderID", orderElement)), 
						new Customer(getValue("CustomerID", orderElement)), 
						Integer.parseInt(getValue("EmployeeID", orderElement)),
						makeTimeStamp(getValue("OrderDate", orderElement)),
						makeTimeStamp(getValue("RequiredDate", orderElement)),
						makeTimeStamp(getValue("ShippedDate", orderElement)),
						new Shipper(Integer.parseInt(getValue("ShipVia", orderElement))),
						Float.parseFloat(getValue("Freight", orderElement)),
						getValue("ShipName", orderElement),
						getValue("ShipAddress", orderElement),
						getValue("ShipCity", orderElement),
						getValue("ShipRegion", orderElement),
						getValue("ShipPostalCode", orderElement),
						getValue("ShipCountry", orderElement),
						null);

				
				HashSet<OrderDetail> orderDetailsSet = new HashSet<>();
				for(int i=0; i<3; i++){
					
					Node orderDetailNode = orderDetails.item(howMany*3+i);
					
					if (orderNode.getNodeType() == Node.ELEMENT_NODE) {
						Element orderDetailElement = (Element) orderDetailNode;

						OrderDetail orderDetail = new OrderDetail(
								Integer.parseInt(getValue("odID", orderDetailElement)),
								order, 
								new Product(Integer.parseInt(getValue("ProductID", orderDetailElement))), 
								Float.parseFloat(getValue("UnitPrice", orderDetailElement)), 
								Integer.parseInt(getValue("Quantity", orderDetailElement)), 
								Float.parseFloat(getValue("Discount", orderDetailElement)) );
						
						orderDetailsSet.add(orderDetail);
					}
				}
				
				data.put(order, orderDetailsSet);
				
				
			}
			lol++;
			howMany++;
		}
		return data;
	}
	
	
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
	private Timestamp makeTimeStamp(String timestamp){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate= null;
		try {
			parsedDate = dateFormat.parse(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Timestamp(parsedDate.getTime());
	}
	
	
}
