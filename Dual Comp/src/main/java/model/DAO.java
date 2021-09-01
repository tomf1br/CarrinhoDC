package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	// Parametros de conexao

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.0.0.199:3306/dbdualcomp?useTimezone=true&serverTimezone=UTC";
	private String user = "dba";
	private String password = "Senac@123";

	/* Conexao */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void testarConexao() {
		try {
			Connection con = conectar();
			System.out.println("Conectado: " + con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Crud Create
	public void inserirProduto(JavaBeans javabeans) {
		String create = "insert into carrinho(produto,quantidade,valor) values(?,?,?)";
		try {
			// abrir conexao
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, javabeans.getProduto());
			pst.setString(2, javabeans.getQuantidade());
			pst.setString(3, javabeans.getValor());
			pst.executeUpdate();
			// encerrar a conexao
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD READ */
	// metodo com retorno criado com a referencia ao vetor dinamico

	public ArrayList<JavaBeans> listarProdutos() {
		// a linha abaixo cria um vetor dinamico
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from carrinho order by produto";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery(); // Passo 3 - slide 22
			while (rs.next()) {
				// recebimento dos dados do banco - Passo 4 - slide 22
				String id = rs.getString(1);
				String produto = rs.getString(2);
				String quantidade = rs.getString(3);
				String valor = rs.getString(4);
				// setar as variáveis JavaBeans - Passo 5 - slide 22
				// a linha abaixo seta o construtor 2 do JavaBeans (vetor)
				produtos.add(new JavaBeans(id, produto, quantidade, valor));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return (null);
		}
	}

	/* CRUD UPDATE */

	/* CRUD DELETE */
}
