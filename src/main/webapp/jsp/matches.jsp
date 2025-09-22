<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.entity.Player" %>
<%@ page import="model.entity.Match" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/TennisBoard/css/style.css">
</head>
<body>
    <a href="/TennisBoard"><p>Go to main page</p></a>
    <a href="/TennisBoard/new-match"><p>Start new match</p></a>
    <a href="/TennisBoard/matches?page=1"><p>finished matches</p></a>
    <form action="/TennisBoard/matches" method="GET">
        <input type="hidden" name="page" value="1">
        <input type="text" name="filter_by_player_name">
        <button type="submit">send</button>
    </form>
    <ul>
        <%
        List<Match> matches = (List<Match>) request.getAttribute("matches");
        for (Match match: matches)  {
        %>
            <li>
                <%
                    Player player1 = match.getPlayer1();
                    Player player2 = match.getPlayer2();
                    Player winner = match.getWinner();
                %>
                <p> first player: <%= player1.getName() %> </p>
                <p> second player: <%= player2.getName() %> </p>
                <p> winner: <%= winner.getName() %> </p>
            </li>
        <%
            }
        %>
        <%
            String name = (String) request.getAttribute("playerName");
        %>
        <%=
            name
        %>
        <p>${name}</p>
        <% if ((Integer) request.getAttribute("previousPage") > 0) { %>
        <form action="/TennisBoard/matches" method="GET">
            <input type="hidden" name="page" value="${previousPage}">
        <% if (name != null) { %>
            <input type="hidden" name="filter_by_player_name" value="<%= name %>">
        <% } %>
            <button type="submit">previousPage</button>
        </form>
        <% } %>
        <%
            if ((Integer) request.getAttribute("pageNumber") < ((Long) request.getAttribute("amount") + 1) / 2) {
        %>
            <a href="/TennisBoard/matches?page=${nextPage}<% if (name != null) { %>&filter_by_player_name=<%= name %><% } %>"><button type="button">next page</button></a>
        <% } %>
        <br>
        page: ${pageNumber}
    </ul>
</body>
</html>
