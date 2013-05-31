package negocio;

import infra.ClienteDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import persistencia.ClienteDAO;
import conexaodb.FerramentaConexao;


public class ClienteBO 
{

	ClienteDAO dao = new ClienteDAO();
	
	public void inserir(ClienteDTO cliente) throws SQLException, Exception 
	{	
		Connection conexao = recuperaConexao();
		
		Boolean valida;
		if (cliente != null){
			valida = validaCPF(cliente.getCpf());
			if(valida == true){
				if(validaCEP(cliente.getCep())== true){
					if(buscaCPF(cliente.getCpf())== false){
						dao.inserir(cliente, conexao);
					}else{
						devolveConexao(conexao);
						throw new Exception("CPF JÁ CADASTRADO");
					}
				}else{
					devolveConexao(conexao);
					throw new Exception("CEP INVALIDO");
				}
			}else{
				devolveConexao(conexao);
				throw new Exception("CPF INVALIDO");
			}
					
		}else{
			devolveConexao(conexao);
			throw new Exception("CLIENTE VAZIO");
		}
		devolveConexao(conexao);
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList consultar(String cpf) throws Exception, SQLException {

		ArrayList lista = new ArrayList();
		Connection conn = recuperaConexao();

		if (cpf !=null) {
			
			if (!cpf.equals("")) {
				
				if(validaCPF(cpf)== true){
					lista = dao.consultar(cpf, conn);
				} else{
					devolveConexao(conn);
					throw new Exception("CPF INVALIDO");
				}
						
			} else {
				
				devolveConexao(conn);
				throw new Exception("CPF vazio");
			}

		} else {
			devolveConexao(conn);
			throw new Exception("CPF inexistente");

		}
		devolveConexao(conn);

		return lista;
}
	
	public void excluir(String cpf) throws Exception, SQLException{
		
		Connection conn = recuperaConexao();
		
		if (cpf != null) {
		if (!cpf.equals("")) {
		dao.excluir(cpf, conn);
		} else {
		throw new Exception("CPF Vazio");
		}
		
		} else {
		
		throw new Exception("CPF NAO INFORMADO");
		}
		this.devolveConexao(conn);
		
	}

	public void alterar(ClienteDTO cliente) throws Exception, SQLException{
		Connection conn = recuperaConexao();
		
		Boolean valida;
		if (cliente != null){
			valida = validaCPF(cliente.getCpf());
			if(valida == true){
				if(validaCEP(cliente.getCep())== true){
						dao.alterar(cliente, conn);
				}else{
					devolveConexao(conn);
					throw new Exception("CEP INVALIDO");
				}
			}else{
				devolveConexao(conn);
				throw new Exception("CPF INVALIDO");
			}
					
		}else{
			devolveConexao(conn);
			throw new Exception("CLIENTE VAZIO");
		}
		
		devolveConexao(conn);
	}
	
	private Connection recuperaConexao() throws Exception 
	{
		return FerramentaConexao.getFerramenta().recuperaConexao();
	}

	private void devolveConexao(Connection c) throws Exception
	{
		FerramentaConexao.getFerramenta().devolveConexao(c);
	}
	
	public boolean validaCEP(String cep){
		
		Boolean existe = cep.matches("^\\d{5}-\\d{3}$");
		
		return existe;
	}
	
	public boolean validaCPF(String cpf){
		Boolean existe = cpf.matches("^\\d{11}$");
		
		return existe;
	}
	
	private boolean buscaCPF (String cpf) throws SQLException, Exception{
		Boolean existe = false;
				
		String busca = consultar(cpf).toString();
		System.out.println(busca);
		if(busca != "[]"){
			if(busca.equals(cpf)){
				existe = true;
			}else{
				throw new Exception("CPF já cadastrado");
			}
		}
		return existe;
	}
}
