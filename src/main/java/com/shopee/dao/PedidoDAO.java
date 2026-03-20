package com.shopee.dao;

import com.shopee.model.Pedido;
import com.shopee.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAO implements DAO<Pedido> {
    private Connection connection;

    public PedidoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Pedido salvar(Pedido p) throws SQLException {
        String sql = "INSERT INTO pedido (cliente_id, total, status) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, p.getClienteId());
        stmt.setDouble(2, p.getTotal());
        stmt.setString(3, p.getStatus());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) p.setId(rs.getInt(1));

        stmt.close();
        return p;
    }

    @Override public List<Pedido> buscarTodos() throws SQLException { return new ArrayList<>(); }
    @Override public void atualizar(Pedido p) throws SQLException { }
    @Override public void deletar(int id) throws SQLException { }
    @Override public Optional<Pedido> buscarPorId(int id) throws SQLException { return Optional.empty(); }
    @Override public long contar() throws SQLException { return 0; }
}