package repository;

import entity.Usuario;
import config.DatabaseConnection;
import util.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//** Implementação dos métodos de persistência utilizando JDBC **/
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nome, cpf, idade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getCpf());
            ps.setInt(4, usuario.getIdade());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao inserir usuário no banco de dados.");
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, cpf = ?, idade = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setInt(3, usuario.getIdade());
            ps.setInt(4, usuario.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao atualizar usuário.");
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao remover usuário.");
            return false;
        }
    }

    @Override
    public Usuario findById(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade")
                );
            }
            return null;

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao buscar usuário pelo ID.");
            return null;
        }
    }

    @Override
    public Usuario findByCpf(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade")
                );
            }
            return null;

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao buscar usuário pelo CPF.");
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade")
                ));
            }

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao listar todos os usuários.");
        }
        return lista;
    }

    @Override
    public List<Usuario> findByNomeInicial(String iniciais) {
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ? ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, iniciais + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade")
                ));
            }

        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao buscar usuários pelas iniciais do nome.");
        }
        return lista;
    }
}
