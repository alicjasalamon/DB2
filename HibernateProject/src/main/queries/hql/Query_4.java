package main.queries.hql;

import java.util.Iterator;
import java.util.List;

import main.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * jaka kwota zamówieñ by³a zg³aszana w ka¿dy z dni tygodnia
 */
public class Query_4 {

	public static void main(String[] args) {

		Query_4 query1_Regular = new Query_4();
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (int j = 0; j < 300; j += 1) {
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
					.createQuery(
							"select dayOfWeek(o.orderDate), sum(od.unitPrice * od.quantity) from OrderDetail od join od.orderID o group by dayOfWeek(o.orderDate)")
					.list();

//			 List results = session.getNamedQuery("query4").list();

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
