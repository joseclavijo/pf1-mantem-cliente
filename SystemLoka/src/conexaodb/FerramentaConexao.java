package conexaodb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class FerramentaConexao {
	
	private static final String URL = "jdbc:mysql://localhost:3306/bd_clientes";
	private static final String USER = "root";
	private static final String PASS = "clavijo";
	@SuppressWarnings("rawtypes")
	private ArrayList listaFerramenta = new ArrayList();
	private static FerramentaConexao ferramenta;  
	private static final int REMOVE = 1;
	private static final int ADICIONA = 2;
	
	@SuppressWarnings("unchecked")
	public FerramentaConexao() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		for(int i=1; i<=10; i++)
		{
			listaFerramenta.add(DriverManager.getConnection(URL,USER,PASS));
		}
	}
	
	public static FerramentaConexao getFerramenta() throws Exception
	{
		if(ferramenta==null)
		{
			ferramenta = new FerramentaConexao();
		}
		return ferramenta;
	}
	
	private Connection getConexao()
	{
		Connection c = null;
		if(listaFerramenta.size()>0)
		{
			c = (Connection)listaFerramenta.remove(0);
		}
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private synchronized Connection manipula(Connection con, int tipo) throws Exception
	{
		
		Connection c = null;
		if(tipo==REMOVE){	
			c = getConexao();
			if(c == null)
			{
				throw new Exception ("Nao existem conexoes disponiveis.");
			}
		}else
		{
			listaFerramenta.add(con);
		}
		return c;
	}
	
	public Connection recuperaConexao() throws Exception
	{
		return manipula(null,REMOVE);
	}
	
	public void devolveConexao(Connection con) throws Exception
	{
		manipula(con,ADICIONA);
	}
}
