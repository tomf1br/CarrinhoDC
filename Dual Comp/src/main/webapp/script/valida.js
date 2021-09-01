/**
 * validacao de campos obrigatorios
 *@author Jerry Ferreira
 */

function validar() {
	//passo 2 slide 21
	let produto = frmProduto.produto.value
	let quantidade = frmProduto.quantidade.value
	let valor = frmProduto.valor.value

	if (produto === '') {
		alert('Preencha o nome do produto')
		frmProduto.produto.focus()
		return false

	} else if (quantidade === '') {

		alert('Preencha a quantidade do produto')
		frmProduto.quantidade.focus()
		return false
	} else if (valor === '') {

		alert('Preencha o valor do produto')
		frmProduto.valor.focus()
		return false
	} else {
		// a linha abaixo envia os dados do formulario para camada controller
		document.forms['frmProduto'].submit()	//passo 3 slide 21
	}
}