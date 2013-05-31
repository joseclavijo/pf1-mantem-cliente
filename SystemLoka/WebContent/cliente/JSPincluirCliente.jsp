<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel=stylesheet type="text/css" href="../css/primeirapagina.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar</title>
</head>
<body>
<div id='cssmenu'>
<ul>
   <li ><a href='../index.html'><span>Home</span></a></li>
   <li class='active'><a href='JSPincluirCliente.jsp'><span>Cadastrar</span></a></li>
   <li><a href='Consultar.jsp'><span>Consultar</span></a></li>
   <li class='last'><a href='sobre.html'><span>Sobre</span></a></li>
</ul>
</div>
	<center>
	<form action="/SystemLoka/mcliente" method="post">
			
			<h3>Incluir</h3>
			
			<table>
			<tr><td>Nome:</td><td><input type="text" name="nome" value="" ></td></tr>
			<tr><td>CPF: </td><td> <input type="text" name="cpf" value="" > *apenas numeros </td></tr>
			<tr><td>Endereço: </td><td><input type="text" name="endereco" value=""></td></tr>
			<tr><td>CEP: </td><td><input type="text" name="cep" value=""> *70000-000 </td></tr>
			<tr><td colspan="2"><center><input type="hidden" name="acao" value="inserir"/>
			<input type="submit" value="Inserir">
			<input type="button" onclick="window.location.href=('/SystemLoka/index.html')" value="Voltar"></center></td></tr>
			</table>
	</form>
	</center>
</body>
</html>