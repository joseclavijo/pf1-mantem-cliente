<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@ page import="infra.ClienteDTO"%>
<% ClienteDTO cliente = (ClienteDTO)request.getAttribute("dto"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar</title>
</head>
<body>
<center>
<form action="/SystemLoka/mcliente" method="post">
			
			<h3>Alterar Cliente</h3>
			<table>
			<tr><td>CPF:</td><td> <input type="text" name="cpf" value=<%=cliente.getCpf()%>>  </td></tr>
			<tr><td>Nome:</td><td>  <input type="text" name="nome" value=<%=cliente.getNome()%>> </td></tr>
			<tr><td>Endereço:</td><td>   <input type="text" name="end" value=<%=cliente.getEndereco()%>></td></tr>
			<tr><td>CEP:</td><td>   <input type="text" name="cep" value=<%=cliente.getCep()%>>  <br></td></tr>
			<tr><td colspan="2"><center> <input type="submit" name="acao" value="validar">
			<input type="button" onclick="window.location.href=('/SystemLoka/index.html')" value="Voltar"> </center></td></tr>
			</table>
</form>
</center>
</body>
</html>