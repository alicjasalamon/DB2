import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


@SuppressWarnings("deprecation")
public class Main {
	
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public static void shutdown() {

		getSessionFactory().close();
	}
	
	public static void main(String[] args) {

		Session newSession = getSessionFactory().openSession();
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

		shutdown();
	}
}