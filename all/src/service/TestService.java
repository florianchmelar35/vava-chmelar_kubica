package service;


import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Stateless session bean-a s jednoduchou sluzbou
 * @author Jaroslav Jakubik
 */
@Stateless
@LocalBean
@Path("/sayHello")
@Remote
public class TestService {

    /**
     * Default constructor.
     */
    public TestService() {
    }

    /**
     * Jednoducha sluzba na stateless bean-e, publikovana cez GET REST
     * @param who
     * @return
     */
    @GET
    public String sayHello(@QueryParam("param1") String who) {
    	return "hello " + who;
    }

}
