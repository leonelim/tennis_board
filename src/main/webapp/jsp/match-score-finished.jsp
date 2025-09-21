<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/TennisBoard/css/style.css">
</head>
<body>
    <a href="/TennisBoard"><p>Go to main page</p></a>
    <a href="/TennisBoard/new-match"><p>Start new match</p></a>
    <a href="/TennisBoard/matches?page=1&filter_by_name=1"><p>finished matches</p></a>
    ${match.getPlayer1().getName()} ${match.getScore1().getPoints()} ${match.getScore1().getGames()} ${match.getScore1().getSets()}
    ${match.getPlayer2().getName()} ${match.getScore2().getPoints()} ${match.getScore2().getGames()} ${match.getScore2().getSets()}
</body>
</html>