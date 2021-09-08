<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Carrinho</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Carrinho</h1>
	<a href="novo.html" class="Botao1">Novo Produto</a>
	<a href="" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Produto</th>
				<th>Quantidade</th>
				<th>Valor</th>
			</tr>
		</thead>
		<tbody>
			<%
			@SuppressWarnings("unchecked")
			// a linha abaixo recebe a lista da camada controller armazenando no vetor
			ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produto");
			for (int i = 0; i < lista.size(); i++) {
			%>

			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getProduto()%></td>
				<td><%=lista.get(i).getQuantidade()%></td>
				<td><%=lista.get(i).getValor()%></td>
				<!-- na linha abaixo o ?(parametro) envia um conteudo ao servlet  -->
				<td><a href="select?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a><a href="javascript:confirmar(<%=lista.get(i).getId()%>)" class="Botao2">Excluir</a></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
	<script src="script/valida.js"></script>
</body>
</html>