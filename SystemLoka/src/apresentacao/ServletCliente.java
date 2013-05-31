package apresentacao;


import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Collection;


import infra.ClienteDTO;
import negocio.ClienteBO;

public class ServletCliente extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		String acao = request.getParameter("acao");
		if("inserir".equals(acao))
		{
			inserir(request, response);
		} else if ("consultar".equals(acao))
		  {
		    consultar(request, response);
		  }else if ("alterar".equals(acao)){
			  alterar(request, response);
		  }else if("excluir".equals(acao)){
			  excluir(request,response);
		  }else if("validar".equals(acao)){
			  validarAlteracao(request,response);
		  }
		
	}
	
	
	private void inserir(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher redir = null;
		
		ClienteBO negocio = new ClienteBO();
		try {
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String endereco = request.getParameter("endereco");
			String cep = request.getParameter("cep");

			ClienteDTO cliente = new ClienteDTO(nome, cpf, endereco, cep);
			
			negocio.inserir(cliente);

			redir = request.getRequestDispatcher("cliente/sucesso.jsp");
			redir.forward(request, response);

		} catch (Exception e) {
			redir = request.getRequestDispatcher("cliente/falha.jsp");
			e.printStackTrace();
			try {

				redir.forward(request, response);

			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
	}

	private void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher redir = null;
		
		ClienteBO negocio = new ClienteBO();
		
		String cpf = request.getParameter("cpf");
		
		@SuppressWarnings("rawtypes")
		Collection lista = null;
		
		try {
			
			lista = negocio.consultar(cpf);
			redir = request.getRequestDispatcher("cliente/resultadoConsulta.jsp");
			request.setAttribute("lista", lista);
			redir.forward(request, response);
			
		} catch (Exception e) {
			redir = request.getRequestDispatcher("cliente/falha.jsp");
			redir.forward(request, response);
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher redir = null;
		
		ClienteBO negocio = new ClienteBO();
		
		String cpf = request.getParameter("cpf");
		
		try {
			negocio.excluir(cpf);
			redir = request.getRequestDispatcher("cliente/sucesso.jsp");
			redir.forward(request, response);
			
		} catch (Exception e) {
			redir = request.getRequestDispatcher("clente/falha.jsp");
			redir.forward(request, response);
			// TODO: handle exception
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void alterar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Collection lista = null;
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String end = request.getParameter("endereco");
		String cep = request.getParameter("cep");
		
		ClienteDTO cliente = new ClienteDTO(nome,cpf,end,cep);
		
		RequestDispatcher redireciona = null;
		
		ClienteBO negocio = new ClienteBO();
		
		try{
			lista = negocio.consultar(request.getParameter("cpf"));
			
			cliente = (ClienteDTO)lista.iterator().next();
			
			redireciona = request.getRequestDispatcher("cliente/JspAlterar.jsp");
			request.setAttribute("dto",cliente);
			
			redireciona.forward(request,response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void validarAlteracao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher redir = null;
		
		ClienteBO negocio = new ClienteBO();
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String end = request.getParameter("end");
		String cep = request.getParameter("cep");
		
		
		
		
		try {	
			ClienteDTO cliente = new ClienteDTO(nome, cpf, end, cep);
			negocio.alterar(cliente);
			
			redir = request.getRequestDispatcher("cliente/sucesso.jsp");
			redir.forward(request, response);
			
		} catch (Exception e) {
			redir = request.getRequestDispatcher("cliente/falha.jsp");
			redir.forward(request, response);
			// TODO: handle exception
		}
	}
}
