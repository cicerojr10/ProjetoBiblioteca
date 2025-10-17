package br.com.ciseravjorn.catalogo.dao;

import br.com.ciseravjorn.catalogo.model.ItemMidia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ItemMidiaDAO {

    public void inserir(ItemMidia item) {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item no banco de dados: ", e);
        }
    }

    public List<ItemMidia> listarTodos() {
        String sql = "SELECT * FROM item_midia";

        List<ItemMidia> itens = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemMidia item = new ItemMidia();
                item.setId(rs.getInt("id"));
                item.setTitulo(rs.getString("titulo"));
                item.setAutorDiretor(rs.getString("autor_diretor"));
                item.setAnoLancamento(rs.getInt("ano_lancamento"));
                item.setGenero(rs.getString("genero"));
                item.setSinopse(rs.getString("sinopse"));
                item.setTipoMidia(rs.getString("tipo_midia"));

                itens.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os items no banco de dados: ", e);
        }
        return itens;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM item_midia WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item no banco de dados: ", e);
        }
    }

    public void atualizar(ItemMidia item) {
        String sql = "UPDATE item_midia " +
                "SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, genero = ?, sinopse = ?, tipo_midia = ? " +
                "WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());
            stmt.setInt(7, item.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item no banco de dados: ", e);
        }
    }

    public ItemMidia buscarPorId(int id) {
        // 1. Qual o comando SQL para buscar um item pelo seu ID?
        String sql = "SELECT * FROM item_midia WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 2. Substitui o "?" do SQL pelo ID que recebemos como parâmetro.
            stmt.setInt(1, id);

            // 3. Executa a query e guarda o resultado.
            ResultSet rs = stmt.executeQuery();

            // 4. Verifica se o banco de dados retornou algum resultado.
            if (rs.next()) {
                // 5. Se encontrou, monta o objeto ItemMidia.
                ItemMidia item = new ItemMidia();
                item.setId(rs.getInt("id"));
                item.setTitulo(rs.getString("titulo")); // Qual o nome da coluna do título?
                item.setAutorDiretor(rs.getString("autor_diretor"));
                item.setAnoLancamento(rs.getInt("ano_lancamento"));
                item.setGenero(rs.getString("genero"));
                item.setSinopse(rs.getString("sinopse"));
                item.setTipoMidia(rs.getString("tipo_midia"));

                // 6. Retorna o objeto preenchido.
                return item;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item por ID: ", e);
        }

        // 7. Se o loop "if" não encontrou nada, retorna null.
        return null;
    }

    public List<ItemMidia> buscarPorTermo(String termo) {
        String sql = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ?";
        List<ItemMidia> itens = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // --- PARTE NOVA ---
            // Preparamos o termo com '%' no início e no fim
            String termoDeBusca = "%" + termo + "%";

            // Definimos os dois parâmetros do SQL
            stmt.setString(1, termoDeBusca);
            stmt.setString(2, termoDeBusca);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // A lógica de popular o objeto é a mesma do listarTodos()
                ItemMidia item = new ItemMidia();
                item.setId(rs.getInt("id"));
                item.setTitulo(rs.getString("titulo"));
                item.setAutorDiretor(rs.getString("autor_diretor"));
                item.setAnoLancamento(rs.getInt("ano_lancamento"));
                item.setGenero(rs.getString("genero"));
                item.setSinopse(rs.getString("sinopse"));
                item.setTipoMidia(rs.getString("tipo_midia"));

                itens.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens por termo: ", e);
        }

        return itens;
    }


}