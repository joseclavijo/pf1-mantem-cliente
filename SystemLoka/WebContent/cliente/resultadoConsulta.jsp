<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="infra.ClienteDTO, java.util.*" %>

<%! private String table(Collection lista){
	StringBuffer sb = new StringBuffer();
	Iterator it = lista.iterator();
	
	sb.append("<table border=1>");
	sb.append("<tr><td><b> Nome </b></td><td><b>CPF</b></td> <td><b>Endereço</b></td> <td><b>CEP</b></td></tr>");
	
	ClienteDTO dto;
	
	while(it.hasNext()){
		dto = (ClienteDTO) it.next();
		sb.append("<tr><td>" + dto.getNome() + "</td><td>" + dto.getCpf() + "</td><td>" + dto.getEndereco() + "</td><td>" + dto.getCep() + "</td></tr>");
		
		sb.append("<td><a href='mcliente?acao=alterar&cpf=" + dto.getCpf() +"'>Alterar</a><br></td>");
		sb.append("<td><a href='mcliente?acao=excluir&cpf=" + dto.getCpf() +"' >Excluir</a><br></td>");
		
	}
	sb.append("</table>");
	
	return sb.toString();
}%>
<%
	Collection col = ((ArrayList) request.getAttribute("lista"));
	String resultPesq = table(col);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado Pesquisar</title>
</head>
<body>
		<BR>
			<center>
				<p>
					<b>Resultado da pesquisa: </b>
				</p>
				<hr>
				<BR>
				<%=resultPesq%>
				<BR>
				<hr>
				<br>
				<input type="button" onclick="window.location.href=('/SystemLoka/index.html')" value="Voltar">
				</center>

</body>
</html>