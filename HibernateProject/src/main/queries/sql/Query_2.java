package main.queries.sql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
/*
 * jaki by³ œredni czas realizacji zamówienia w ka¿dym roku
 */
public class Query_2 {

	public static void main(String[] args) {

		Query_2 query1_Regular = new Query_2();
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (int i = 0; i < 500; i += 1) {
			System.out.println(query1_Regular.test(session));
		}

		session.close();

	}
	
	long test(Session session) {
		
		Transaction tx = null;
		long before=0, after=0;
		try {
			
			before = System.currentTimeMillis();
			tx = session.beginTransaction();

//			List results = session
//			.createSQLQuery(
//					"select avg(DATEDIFF(ShippedDate, OrderDate)), year(ShippedDate) from orders group by year(ShippedDate)")
//			.list();

			List results = session.getNamedQuery("query2").list();

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
