package br.com.ciseravjorn.catalogo.controller;

import br.com.ciseravjorn.catalogo.dao.ItemMidiaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/excluir-item")
public class ExcluirItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. PEGAR O PARÂMETRO ID DA URL
        // Usamos request.getParameter("id") para pegar o valor que veio depois de "?id="
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        // 2. USAR O DAO PARA EXCLUIR O ITEM
        ItemMidiaDAO dao = new ItemMidiaDAO();
        dao.excluir(id);

        // 3. REDIRECIONAR O USUÁRIO DE VOLTA PARA A LISTA
        // Após excluir, mandamos o usuário para o Servlet de listagem,
        // que vai buscar a lista atualizada no banco e exibi-la.
        response.sendRedirect("listar-itens");
    }
}