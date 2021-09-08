<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Carrinho - Editar produto</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Produto</h1>
	<form name="frmProduto" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" readonly id="caixa3" value="<%out.println(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="produto" class="Caixa1" value="<%out.println(request.getAttribute("produto"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="quantidade" class="Caixa2" value="<%out.println(request.getAttribute("quantidade"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="valor" class="Caixa1" value="<%out.println(request.getAttribute("valor"));%>"></td>
			</tr>
		</table>
		<input type="button" value="salvar" class="Botao1" onclick="validar()">
	</form>
	<!-- a linha abaixo faz a ligacao com o documento JS -->
	<script src="script/valida.js"></script>
</body>
</html>