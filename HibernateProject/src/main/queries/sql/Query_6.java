package main.queries.sql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;
import mapping.Product;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * jaka by³a œrednia wartoœæ jednej sztuki produktu dla ka¿dego ze spedytorów w ka¿dym roku
 */
public class Query_6 {

	public static void main(String[] args) {

		Query_6 query1_Regular = new Query_6();

		Session session = HibernateUtil.getSessionFactory().openSession();

			for (int j = 0; j < 200; j += 1) {
				System.out.println(query1_Regular.test(session));
			}
	}

	
	long test(Session session) {

		Transaction tx = null;
		long before = 0, after = 0;
		try {

			before = System.currentTimeMillis();
			tx = session.beginTransaction();

			List results = session.createSQLQuery("select CompanyName, year(ShippedDate), avg(UnitPrice) from shippers inner join orders on ShipperID = ShipVia inner join orderdetails on orderdetails.OrderID = orders.OrderID group by CompanyName, year(ShippedDate)").list();

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
