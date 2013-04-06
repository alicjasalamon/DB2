import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Main {
	
	private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

	static {
		try {
            Configuration configuration = new Configuration().configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
           
		} catch (HibernateException he) {
			throw new ExceptionInInitializerError(he);
		}
	}

	
	public static void main(String[] args) {

		getOrders();
	}
	
	static void getEmployees()
	{
		Session newSession = sessionFactory.openSession();
		Transaction newTransaction = newSession.beginTransaction();
		List<Employee> employeesList = newSession.createSQLQuery(
				"select * from Employees").addEntity(Employee.class).list();
		System.out.println(employeesList.size() + " message(s) found:");

		for (Iterator<Employee> iter = employeesList.iterator(); iter.hasNext();) {
			Employee loadedEmp = (Employee) iter.next();
			System.out.println(loadedEmp.getFirstName() + " "
			+loadedEmp.getLastName()+ " " + loadedEmp.getCity());
		}
		newTransaction.commit();
		newSession.close();

		sessionFactory.close();
	}
	
	static void getOrders()
	{
		Session newSession = sessionFactory.openSession();
		Transaction newTransaction = newSession.beginTransaction();
		List<Order> ordersList = newSession.createSQLQuery(
				"select * from Orders").addEntity(Order.class).list();
		System.out.println(ordersList.size() + " message(s) found:");

		for (Iterator<Order> iter = ordersList.iterator(); iter.hasNext();) {
			Order order = (Order) iter.next();
			Employee empl = order.getEmployeeID();
			Customer cust = order.getCustomerID();
			System.out.println(order.getOrderID() + " " + empl.getFirstName() + " " + cust.getCompanyName());
		}
		newTransaction.commit();
		newSession.close();

		sessionFactory.close();
	}
	
}