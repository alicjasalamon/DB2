package main.queries.sql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * jaka by³a wartoœæ produktów zamówionych z ka¿dego z krajów w ka¿dym roku; chodzi o kraj zamawiaj¹cego
 */
public class Query_5 {

	public static void main(String[] args) {

		Query_5 query1_Regular = new Query_5();
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (int j = 0; j < 40; j += 1) {
			System.out.println(query1_Regular.test(session));
		}

		session.close();
	}

	long test(Session session) {

		Transaction tx = null;
		long before = 0, after = 0;
		try {

			before = System.currentTimeMillis();
			tx = session.beginTransaction();

//			List results = session
//					.createSQLQuery(
//							"select Country, YEAR(OrderDate), sum(UnitPrice*Quantity) from orderdetails inner join orders on orderdetails.OrderID = orders.OrderID inner join customers on orders.OrderID = orderdetails.orderID group by Country, YEAR(OrderDate)")
//					.list();
			
			 List results =
			 session.createSQLQuery("call proc_query5()").list();

			Iterator ite = results.iterator();

			while (ite.hasNext()) {
				Object[] objects = (Object[]) ite.next();
				// System.out.println(objects[0] + " " + objects[1]);
			}

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			tx.commit();
			after = System.currentTimeMillis();
			session.flush();
		}

		return after - before;

	}

}
