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
    <a href="/TennisBoard/matches?page=1&filter_by_name=1"><p>finished matches</p></a>
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
            if ((Integer) request.getAttribute("previousPage") > 0) {
        %>
            <a href="/TennisBoard/matches?page=${previousPage}&filter_by_name=1"><button type="button">previous page</button></a>
        <%
            }
        %>
        <%
            if ((Integer) request.getAttribute("pageNumber") < ((Long) request.getAttribute("amount") + 1) / 2) {
        %>
            <a href="/TennisBoard/matches?page=${nextPage}&filter_by_name=1"><button type="button">next page</button></a>
        <%
            }
        %>
        <br>
        page: ${pageNumber}
    </ul>
</body>
</html>
