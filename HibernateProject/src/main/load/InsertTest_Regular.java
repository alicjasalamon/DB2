package main.load;
import java.util.HashMap;
import java.util.HashSet;

import main.HibernateUtil;
import mapping.Order;
import mapping.OrderDetail;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InsertTest_Regular {

	OrdersParser ordersParser = new OrdersParser();
	
	public static void main(String[] args) {
		
		InsertTest_Regular insertTest = new InsertTest_Regular();
		insertTest.ordersParser.setMode(1);
		
		for(int j = 0; j<10000; j+=1000){
			System.out.println(insertTest.test(j));
		}
		
		insertTest.ordersParser.setMode(2);
		
		for(int j = 0; j<20000; j+=1000){
			System.out.println(insertTest.test(j));
		}
		
		CacheManager.getInstance().shutdown();
	}
	
	long test(int j)
	{
		
		HashMap<Order,HashSet< OrderDetail>> data = ordersParser.get1000Orders(j);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		long begin =0, after=0;
		
		try {
			begin = System.currentTimeMillis();
			tx = session.beginTransaction();
		
			for ( Order order : data.keySet()) {
			    session.save(order);
			    
			    for(OrderDetail orderDetail : data.get(order)){

			    	session.save(orderDetail);
			    }
			}
			
		} catch(RuntimeException e){
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally{ 

			tx.commit();
			after = System.currentTimeMillis();
	        session.flush();
			session.close();
		}
	
		return after-begin;
	}


}