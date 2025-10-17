<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Catálogo de Mídias</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <%-- Seus estilos <style> podem continuar aqui sem problemas --%>
    <style>
        table { width: 100%; border-collapse: collapse; }

        th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .actions { width: 15%; }
    </style>
</head>
<body>

<%-- ### MUDANÇA 1: O TÍTULO VIROU UMA BARRA DE NAVEGAÇÃO COM OS BOTÕES ### --%>
<nav>
    <ul>
        <li><strong>Meu Catálogo</strong></li>
    </ul>
    <ul>
        <li><a href="#" data-theme-switcher="light">Light</a></li>
        <li><a href="#" data-theme-switcher="dark">Dark</a></li>
    </ul>
</nav>

<p><a href="cadastro-item.jsp">Adicionar Novo Item</a></p>

<form action="listar-itens" method="get">
    <input type="text" name="busca" size="50" placeholder="Buscar por título ou autor/diretor...">
    <button type="submit">Buscar</button>
</form>
<hr>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Autor/Diretor</th>
        <th>Ano</th>
        <th>Gênero</th>
        <th>Tipo</th>
        <th class="actions">Ações</th>
    </tr>
    </thead>
    <tbody>
    <%-- Aqui está a mágica do JSTL.
    O forEach vai percorrer a lista que o Servlet nos enviou com o nome "catalogo".
    Para cada 'item' na lista, ele vai repetir o bloco de código <tr>...</tr> --%>
    <c:forEach var="item" items="${catalogo}">
        <tr>
                <%-- A sintaxe ${item.algumaCoisa} chama o método item.getAlgumaCoisa() --%>
            <td>${item.id}</td>
            <td>${item.titulo}</td>
            <td>${item.autorDiretor}</td>
            <td>${item.anoLancamento}</td>
            <td>${item.genero}</td>
            <td>${item.tipoMidia}</td>
            <td>
                    <%-- Deixamos um espaço para os botões de Editar e Excluir no futuro --%>
                <a href="editar-item?id=${item.id}">Editar</a>
                <a href="excluir-item?id=${item.id}">Excluir</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%-- ### MUDANÇA 2: O CÓDIGO JAVASCRIPT FOI ADICIONADO AQUI NO FINAL ### --%>
<script>
    const themeSwitcher = {
        _scheme: 'auto',
        menuTarget: 'details[role="list"]',
        buttonsTarget: 'a[data-theme-switcher]',
        buttonAttribute: 'data-theme-switcher',
        rootAttribute: 'data-theme',
        localStorageKey: 'pico-theme',
        init() {
            this.scheme = this.localStorage.get();
            this.initSwitchers();
        },
        get localStorage() {
            return {
                get: () => {
                    try {
                        return localStorage.getItem(this.localStorageKey);
                    } catch (err) {
                        return null;
                    }
                },
                set: (value) => {
                    try {
                        localStorage.setItem(this.localStorageKey, value);
                    } catch (err) {}
                },
            };
        },
        set scheme(scheme) {
            if (scheme == 'auto') {
                this._scheme = 'auto';
            } else if (scheme == 'dark' || scheme == 'light') {
                this._scheme = scheme;
            }
            this.applyScheme();
            this.localStorage.set(this._scheme);
        },
        get scheme() {
            return this._scheme;
        },
        applyScheme() {
            document.querySelector('html').setAttribute(this.rootAttribute, this.scheme);
        },
        initSwitchers() {
            const switchers = document.querySelectorAll(this.buttonsTarget);
            switchers.forEach(switcher => {
                switcher.addEventListener('click', (event) => {
                    event.preventDefault();
                    this.scheme = switcher.getAttribute(this.buttonAttribute);
                }, false);
            });
        },
    };
    themeSwitcher.init();
</script>

</body>
</html>