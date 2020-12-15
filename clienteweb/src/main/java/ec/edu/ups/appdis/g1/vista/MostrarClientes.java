package ec.edu.ups.appdis.g1.vista;

import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.appdis.g1.modelo.Cliente;
import ec.edu.ups.appdis.g1.negocio.ClienteONRemoto;

/**
 * Servlet implementation class MostrarClientes
 */
@WebServlet("/MostrarClientes")
public class MostrarClientes extends HttpServlet {
	public ClienteONRemoto cr;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarClientes() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("Mostrar cliente"+"\n");
		try {
			this.referenciarONCliente();
		} catch (Exception e) {
			System.out.print("Error al conetar");
			e.printStackTrace();
		}
		try {
			String cedula = "0107137416";
			Cliente p = cr.buscarPersona(cedula);
			response.getWriter().println(p.getApellidos());
			System.out.println("Buscado OK");
		} catch (Exception ex) {
			response.getWriter().println("Base de datos vacia");
			ex.printStackTrace();

		}
		/*
		 * List<Cliente> a; try { a = (List<Cliente>) cr.buscarPersona("0107137416");
		 * response.getWriter().println("Llega"); for (int i = 0; i < a.size(); i++) {
		 * try { String email = a.get(i).getApellidos(); String password =
		 * a.get(i).getCedula(); response.getWriter().println(a.get(i).getNombre() +
		 * "\n"); response.getWriter().println(a.get(i).getApellidos() + "\n");
		 * response.getWriter().println(a.get(i).getFechaNacimiento()+ "\n"); } catch
		 * (Exception ex) {
		 * 
		 * System.out.println("Error al buscar"); }} } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// ContactoEJBBeanRemoto contactoEJBCliente= cr.intanciarEJBTelRemoto();
		/*
		 * listap = cr.buscarPersona.listarAll(p); if(listap!=null) { for (Cliente pe :
		 * listap) { response.getWriter().println(pe.getNombre()+"\n");
		 * response.getWriter().println(pe.getCedula()); } }else
		 * response.getWriter().println("Esta vacia");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		// TODO Auto-generated method stub
		 response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void referenciarONCliente() throws Exception {
		try {
			final Hashtable<String, Comparable> jndiProperties = new Hashtable<String, Comparable>();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			jndiProperties.put("jboss.naming.client.ejb.context", true);

			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "pepe");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "pepe");

			final Context context = new InitialContext(jndiProperties);

			final String lookupName = "ejb:/servidor/ClienteON!ec.edu.ups.appdis.g1.negocio.ClienteONRemoto";

			this.cr = (ClienteONRemoto) context.lookup(lookupName);

		} catch (Exception ex) {
			System.out.println("Error iniciar referencia");
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
