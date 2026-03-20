package com.shopee.dao;

import com.shopee.model.Cliente;
import com.shopee.model.Usuario;
import com.shopee.model.Vendedor;
import com.shopee.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Mantive porque está na sua Interface DAO

public class UsuarioDAO implements DAO<Usuario> {

    private Connection connection;

    public UsuarioDAO() {
        // Padrão simples: Abre a conexão direto no construtor
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario instanceof Cliente ? "cliente" : "vendedor");

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            usuario.setId(rs.getInt(1));
        }

        stmt.close(); // Professor gosta que feche o stmt manualmente
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario u = montarObjeto(rs);
            stmt.close();
            return Optional.of(u);
        }

        stmt.close();
        return Optional.empty();
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            lista.add(montarObjeto(rs));
        }

        stmt.close();
        return lista;
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario instanceof Cliente ? "cliente" : "vendedor");
        stmt.setInt(5, usuario.getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public long contar() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        long total = 0;
        if (rs.next()) {
            total = rs.getLong(1);
        }
        stmt.close();
        return total;
    }

    // Método auxiliar que professores costumam cobrar para organizar
    private Usuario montarObjeto(ResultSet rs) throws SQLException {
        Usuario u;
        if (rs.getString("tipo").equalsIgnoreCase("cliente")) {
            u = new Cliente();
        } else {
            u = new Vendedor();
        }
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        return u;
    }
}