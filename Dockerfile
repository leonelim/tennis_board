FROM tomcat:10

COPY target/TennisBoard.war /usr/local/tomcat/webapps

EXPOSE 8080
