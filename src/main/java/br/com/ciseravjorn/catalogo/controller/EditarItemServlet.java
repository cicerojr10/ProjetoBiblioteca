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

@WebServlet("/editar-item")
public class EditarItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--- DEBUG: EditarItemServlet FOI ACIONADO! ID recebido: " + request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));

        ItemMidiaDAO dao = new ItemMidiaDAO();
        ItemMidia item = dao.buscarPorId(id);

        request.setAttribute("item", item);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-item.jsp");
        dispatcher.forward(request, response);

    }
}