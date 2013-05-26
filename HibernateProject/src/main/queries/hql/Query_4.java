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
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			for (int j = 0; j < 500; j += 1) {
				System.out.println(j + " " + query1_Regular.test(session));
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

//		List results = session.createQuery( "select dayOfWeek(o.orderDate), sum(od.unitPrice) from OrderDetail od join od.orderID o group by dayOfWeek(o.orderDate)" ).list();

		List results = session.getNamedQuery("query4").list();
		Iterator ite = results.iterator();
		while (ite.hasNext()) {
			Object[] objects = (Object[]) ite.next();
		//	System.out.println(objects[0] + " " + objects[1]);
		}

		long after = System.currentTimeMillis();

		return after - before;
	}
	

}
