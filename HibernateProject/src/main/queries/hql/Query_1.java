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
		for (int i = 0; i < 500; i++) {

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

			List results = session
					.createQuery(
							"select c.country, count(o.orderID) from Order o join o.customerID c group by c.country")
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
