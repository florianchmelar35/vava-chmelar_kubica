package application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import service.TestService;

/**
 * Application class-a pre podporu REST controller-a
 * @author Jaroslav Jakubik
 */
@ApplicationPath("")
public class EjbApplication extends Application {

	/**
	 * Registracia bean-y do REST aplikacie
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> cls = new HashSet<>();
		cls.add(TestService.class);
		return cls;
	}

}
