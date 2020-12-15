package ec.edu.ups.appdis.g1.negocio;



import ec.edu.ups.appdis.g1.modelo.Cliente;


public interface ClienteONRemoto {
	public boolean registrarPersona(Cliente cliente) throws Exception;
        public Cliente buscarPersona(String cedula) throws Exception;

}
