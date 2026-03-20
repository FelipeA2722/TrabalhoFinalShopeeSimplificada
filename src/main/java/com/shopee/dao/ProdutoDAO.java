package com.shopee.dao;

import com.shopee.model.Produto;
import com.shopee.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO implements DAO<Produto> {
    private Connection connection;

    public ProdutoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Produto salvar(Produto p) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco, estoque) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPreco());
        stmt.setInt(3, p.getEstoque());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) p.setId(rs.getInt(1));

        stmt.close();
        return p;
    }

    @Override
    public List<Produto> buscarTodos() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
            p.setEstoque(rs.getInt("estoque"));
            lista.add(p);
        }
        stmt.close();
        return lista;
    }

    @Override
    public void atualizar(Produto p) throws SQLException {
        String sql = "UPDATE produto SET nome=?, preco=?, estoque=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPreco());
        stmt.setInt(3, p.getEstoque());
        stmt.setInt(4, p.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    // Métodos obrigatórios da interface (podes deixar simples)
    @Override public Optional<Produto> buscarPorId(int id) throws SQLException { return Optional.empty(); }
    @Override public long contar() throws SQLException { return 0; }
}