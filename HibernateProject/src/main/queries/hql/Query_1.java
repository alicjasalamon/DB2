package main.queries.hql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
/*
 * ile zamówieñ z ka¿dego kraju zosta³o zrealizowanych
 */
public class Query_1 {

	public static void main(String[] args) throws InterruptedException {

		Query_1 query1_Regular = new Query_1();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			for (int i = 0; i < 1000; i++) {

				System.out.println(query1_Regular.test(session));
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

	}

	long test(Session session) {
		long before = System.currentTimeMillis();
		
		List results = session.createQuery("select c.country, count(o.orderID) from Order o join o.customerID c group by c.country" ).list();


		Iterator ite = results.iterator();

		while (ite.hasNext()) {
			Object[] objects = (Object[]) ite.next();
			// System.out.println(objects[0] + " " + objects[1]);
		}

		long after = System.currentTimeMillis();

		return after - before;

	}

}
