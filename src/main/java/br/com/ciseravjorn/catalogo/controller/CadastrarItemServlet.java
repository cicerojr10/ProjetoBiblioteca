package br.com.ciseravjorn.catalogo.controller;

import br.com.ciseravjorn.catalogo.dao.ItemMidiaDAO;
import br.com.ciseravjorn.catalogo.model.ItemMidia;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// A anotação @WebServlet diz ao servidor que esta classe
// é responsável por atender requisições na URL "/cadastrar-item".
@WebServlet("/cadastrar-item")
public class CadastrarItemServlet extends HttpServlet {

    // Como o formulário usa method="post", a lógica vai dentro do método doPost.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. PEGAR OS DADOS DO FORMULÁRIO
        String titulo = request.getParameter("titulo");
        String autorDiretor = request.getParameter("autorDiretor");
        int anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
        String genero = request.getParameter("genero");
        String sinopse = request.getParameter("sinopse");
        String tipoMidia = request.getParameter("tipoMidia");

        // Tenta pegar o ID. Se for um cadastro novo, ele será nulo.
        String idParam = request.getParameter("id");

        // 2. MONTAR O OBJETO ITEMMIDIA
        ItemMidia item = new ItemMidia();
        item.setTitulo(titulo);
        item.setAutorDiretor(autorDiretor);
        item.setAnoLancamento(anoLancamento);
        item.setGenero(genero);
        item.setSinopse(sinopse);
        item.setTipoMidia(tipoMidia);

        // 3. DECIDIR SE É INSERÇÃO OU ATUALIZAÇÃO
        ItemMidiaDAO dao = new ItemMidiaDAO();

        if (idParam == null || idParam.isEmpty()) {
            // Se NÃO HÁ ID, é um item novo. Chama o método inserir.
            dao.inserir(item);
        } else {
            // Se HÁ ID, é uma edição. Converte o ID para int e chama o método atualizar.
            int id = Integer.parseInt(idParam);
            item.setId(id);
            dao.atualizar(item);
        }

        // 4. REDIRECIONAR O USUÁRIO PARA A LISTA
        response.sendRedirect("listar-itens");
    }
}