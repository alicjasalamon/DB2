import java.util.*;
import org.hibernate.*;

public class Main {

	public static void main(String[] args) {

		// CREATE
		addOrder();

		// READ
		readOrders();

		// UPDATE
		updateUser();
		readOrders();

		// DELETE
		deleteCustomer();
	}

	static private void addOrder() {

		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			Customer c = new Customer("AXAMI", "Aksamit INC.");
			session.save(c);

			Employee e = new Employee("Ala", "Salamon", "Krakow");
			session.save(e);

			Order o = new Order(e, c);
			session.save(o);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	static void updateUser() {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			Customer c = new Customer();
			c.customerID = "AXAMI";
			c.companyName = "Axamit & Sons";
			session.update(c);
			
			Employee e = new Employee("Salamon", "Ala", "Krakow");
			e.EmployeeID=10;
			session.update(e);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	static void deleteCustomer() {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			Order o = new Order();
			o.setOrderID(11078);
			session.delete(o);

			Customer c1 = new Customer();
			c1.setCustomerID("AXAMI");
			session.delete(c1);

			Employee e = new Employee();
			e.EmployeeID = 10;
			session.delete(e);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	static void readOrders() {

		Session newSession = HibernateUtil.getSessionFactory().openSession();
		Transaction newTransaction = newSession.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Order> ordersList = newSession
				.createSQLQuery("select * from Orders where OrderID=11078")
				.addEntity(Order.class).list();

		for (Iterator<Order> iter = ordersList.iterator(); iter.hasNext();) {
			Order order = (Order) iter.next();
			Employee empl = order.getEmployeeID();
			Customer cust = order.getCustomerID();
			System.out.println(order.getOrderID() + " by "
					+ empl.getFirstName() + " " + empl.getLastName() + " for "
					+ cust.getCompanyName());
		}
		newTransaction.commit();
		newSession.close();

		HibernateUtil.getSessionFactory().close();
	}

}