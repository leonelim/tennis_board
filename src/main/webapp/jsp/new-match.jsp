<!DOCTYPE html>
<html>
<body>
    <a href="http://localhost:8080/TennisBoard/matches?page=1&filter_by_name=1"><p>finished matches</p></a>
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