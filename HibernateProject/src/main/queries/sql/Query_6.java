package main.queries.sql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;
import mapping.Product;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Query_6 {

	public static void main(String[] args) {

		Query_6 query1_Regular = new Query_6();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();

			for (int j = 0; j < 200; j += 1) {
				System.out.println(j + "\t" + query1_Regular.test(session));
			}

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			tx.commit();
			session.flush();
			session.close();
		}
		CacheManager.getInstance().shutdown();
	}

	long test(Session session) {
		
		long before = System.currentTimeMillis();
		List result = session.createSQLQuery("select CompanyName, year(ShippedDate), sum(UnitPrice)/sum(Quantity) from shippers inner join orders on ShipperID = ShipVia inner join orderdetails on orderdetails.OrderID = orders.OrderID group by CompanyName, year(ShippedDate)").list();

		Iterator ite = result.iterator();
		while (ite.hasNext()) {
			Object[] objects = (Object[]) ite.next();
			//System.out.println(objects[0] + " " + objects[1] + " " + objects[2]);
		}

		long after = System.currentTimeMillis();

		return after - before;
	}

}
