<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/TennisBoard/css/style.css">
</head>
<body>
    <a href="/TennisBoard"><p>Go to main page</p></a>
    <a href="/TennisBoard/new-match"><p>Start new match</p></a>
    <a href="/TennisBoard/matches?page=1"><p>finished matches</p></a>
    <form action="/TennisBoard/new-match" method="post">
        <label for="name1">name 1: </label>
        <input type="text" id="name1" name="name1" required/>

        <label for="name2">name 2:</label>
        <input type="text" id="name2" name="name2" required/>
        <br>
        <br>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>