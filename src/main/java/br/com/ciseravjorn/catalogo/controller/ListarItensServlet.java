package br.com.ciseravjorn.catalogo.controller;

import br.com.ciseravjorn.catalogo.dao.ItemMidiaDAO;
import br.com.ciseravjorn.catalogo.model.ItemMidia;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// A anotação @WebServlet é crucial. Ela que transforma a classe em um Servlet
// e a mapeia para a URL "/listar-itens".
@WebServlet("/listar-itens")
// A palavra-chave "extends" faz nossa classe herdar todo o comportamento de um Servlet.
public class ListarItensServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Tenta pegar o parâmetro de busca do formulário
        String termoBusca = request.getParameter("busca");

        ItemMidiaDAO dao = new ItemMidiaDAO();
        List<ItemMidia> listaItens;

        // 2. Decide qual método do DAO chamar
        if (termoBusca != null && !termoBusca.isEmpty()) {
            // Se HÁ um termo de busca, chama o método de busca
            listaItens = dao.buscarPorTermo(termoBusca);
        } else {
            // Se NÃO HÁ termo de busca, chama o método que lista tudo
            listaItens = dao.listarTodos();
        }

        // 3. O resto do código é exatamente o mesmo de antes
        request.setAttribute("catalogo", listaItens);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-itens.jsp");
        dispatcher.forward(request, response);
    }
}
