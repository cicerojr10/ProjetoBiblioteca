<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>${empty item ? 'Cadastro de Novo Item' : 'Editar Item'}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body>

<%-- ### TAG DE ABERTURA DO CONTAINER VAI AQUI ### --%>
<main class="container">

    <h1>${empty item ? 'Adicionar Novo Item ao Catálogo' : 'Editar Item'}</h1>

    <form action="cadastrar-item" method="post">

        <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}">
        </c:if>

        <%-- Removi as <div> e <br> desnecessárias, o Pico.css organiza o espaçamento --%>
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required value="${item.titulo}">

        <label for="autorDiretor">Autor/Diretor:</label>
        <input type="text" id="autorDiretor" name="autorDiretor" value="${item.autorDiretor}">

        <%-- Coloquei os campos menores lado a lado com a classe "grid" --%>
        <div class="grid">
            <div>
                <label for="anoLancamento">Ano de Lançamento:</label>
                <input type="number" id="anoLancamento" name="anoLancamento" value="${item.anoLancamento}">
            </div>
            <div>
                <label for="genero">Gênero:</label>
                <input type="text" id="genero" name="genero" value="${item.genero}">
            </div>
        </div>

        <label for="tipoMidia">Tipo de Mídia (Livro, Filme, etc.):</label>
        <input type="text" id="tipoMidia" name="tipoMidia" required value="${item.tipoMidia}">

        <label for="sinopse">Sinopse:</label>
        <textarea id="sinopse" name="sinopse" rows="4">${item.sinopse}</textarea>

        <%-- Botão Salvar e o novo botão/link Cancelar, lado a lado --%>
        <div class="grid">
            <button type="submit">Salvar Item</button>
            <a href="listar-itens" role="button" class="secondary">Cancelar</a>
        </div>
    </form>

</main>
<%-- ### TAG DE FECHAMENTO DO CONTAINER VAI AQUI ### --%>

</body>
</html>