package main.queries.sql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;
import mapping.OrderDetail;
import mapping.Product;
import mapping.Supplier;
import net.sf.ehcache.CacheManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * ile sztuk produktów od ka¿dego z dostawców uda³o siê sprzedaæ
 */
public class Query_3 {

	public static void main(String[] args) {

		Query_3 query1_Regular = new Query_3();
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (int j = 0; j < 400; j += 1) {
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

			List results = session
					.createSQLQuery(
							"select CompanyName, sum(Quantity) from suppliers inner join products on suppliers.SupplierID = products.SupplierID inner join orderdetails on products.ProductID = orderdetails.ProductID inner join orders on orderdetails.OrderID = orders.OrderID group by CompanyName")
					.list();

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
