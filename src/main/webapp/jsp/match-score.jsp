<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/TennisBoard/css/style.css">
</head>
<body>
    <a href="/TennisBoard"><p> Home page</p></a>
    <a href="/TennisBoard/matches?page=1&filter_by_name=1"><p>finished matches</p></a>
    <p>
        ${match.getPlayer1().getName()} ${match.getScore1().getPoints()} ${match.getScore1().getGames()} ${match.getScore1().getSets()}
        <form action="/TennisBoard/match-score" method="post">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="hidden" name="point_winner_id" value="${match.getPlayer1().getId()}">
            <input type="submit" value="increment">
        </form>
    </p>
    <p>
        ${match.getPlayer2().getName()} ${match.getScore2().getPoints()} ${match.getScore2().getGames()} ${match.getScore2().getSets()}
    </p>
        <form action="/TennisBoard/match-score" method="post">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="hidden" name="point_winner_id" value="${match.getPlayer2().getId()}">
            <input type="submit" value="increment">
        </form>
</body>
</html>
