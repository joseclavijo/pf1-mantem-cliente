package persistencia;

import infra.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO 
{

	public void inserir(ClienteDTO cliente, Connection conn)
			throws SQLException {

		PreparedStatement pst = null;

		pst = conn.prepareStatement("insert into clientes (nome, cpf, end, cep) values (?, ?, ?, ?)");

		pst.setString(1, cliente.getNome());
		pst.setString(2, cliente.getCpf());
		pst.setString(3, cliente.getEndereco());
		pst.setString(4, cliente.getCep());

		pst.executeUpdate();
		pst.close();

	}
	
	public ArrayList<ClienteDTO> consultar(String cpf, Connection conn)throws SQLException {

		ArrayList<ClienteDTO> lista = new ArrayList<ClienteDTO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement("select * from clientes where cpf like ?");

		if (cpf != null) {
			pst.setString(1,cpf);
		} //else {
			//pst.setString(1, cpf + "%");
		//}
		rs = pst.executeQuery();

		while (rs.next()) {
			ClienteDTO usrdto = new ClienteDTO(rs.getString("nome"),
					rs.getString("cpf"),
					rs.getString("end"),
					rs.getString("cep"));
			lista.add(usrdto);
		}

		rs.close();
		pst.close();

		return lista;

}

	public void excluir(String cpf, Connection conn) throws SQLException {
		PreparedStatement pst = null;
		
		pst = conn.prepareStatement("DELETE FROM clientes WHERE cpf = ?;");
		pst.setString(1, cpf);
		
		pst.executeUpdate();
		pst.close();
		
		
	}
	
	public void alterar(ClienteDTO cliente, Connection conn) throws SQLException {

		PreparedStatement pst = null;
		pst = conn.prepareStatement("update clientes set nome= ?, end = ?, cep = ? where cpf = ?;");
		
		pst.setString(1, cliente.getNome());
		pst.setString(2, cliente.getEndereco());
		pst.setString(3, cliente.getCep());
		pst.setString(4, cliente.getCpf());
		
		pst.executeUpdate();
		pst.close();

	}
}
