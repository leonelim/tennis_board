<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <ul>
        <c:forEach var="match" items="${matches}">
            <li>
                ${match.getPlayer1()}
                ${match.getPlayer2()}
                ${match.getWinner()}
            </li>
        </c:forEach>

    </ul>
</body>
</html>
