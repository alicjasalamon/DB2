package main.load;
import java.util.HashMap;
import java.util.HashSet;

import main.HibernateUtil;
import mapping.Order;
import mapping.OrderDetail;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class InsertTest_Optimized {

	OrdersParser ordersParser = new OrdersParser();
	
	public static void main(String[] args) {
		
		InsertTest_Optimized insertTest = new InsertTest_Optimized();
		insertTest.ordersParser.setMode(1);
		
		for(int j = 0; j<10000; j+=1000){
			System.out.println("czas: " + j + " \t" + insertTest.test(j));
		}
		
		insertTest.ordersParser.setMode(2);
		
		for(int j = 0; j<20000; j+=1000){
			System.out.println("czas: " + j + " \t" + insertTest.test(j));
		}
		
	}
	
	
	long test(int j)
	{
		
		HashMap<Order,HashSet< OrderDetail>> data = ordersParser.get1000Orders(j);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		long begin =0, after=0;
		
		try {

			tx = session.beginTransaction();
			begin = System.currentTimeMillis();
			
			for ( Order order : data.keySet()) {
//				System.out.println("-----" + i);
//				System.out.println("order: " + order.getOrderID());
				
			    session.save(order);
			    
			    for(OrderDetail orderDetail : data.get(order)){

//					System.out.println("\torderdetail: " + orderDetail.getOdID());
			    	session.save(orderDetail);
			    }
			    
			}
				
		} catch(RuntimeException e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally{ 
			after = System.currentTimeMillis();
			tx.commit();
	        session.flush();
			session.close();
		}
		CacheManager.getInstance().shutdown();
		return after-begin;
	}
}