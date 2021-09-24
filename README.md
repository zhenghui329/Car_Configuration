# Car_Configuration

In this academic project, I developed a Java web application for users to select a model and its options to configure a car. I designed a well-encapsulated structure and applied strong programming competency to implement a thread-safe, scalable app. I also created a web-based user interface combining Java and HTML to accept users' selection and provide results.

To test this project:
- Set the server IP:
  1) Client project: *Client/src/driver/ClientDriver.java*(change the argument)
  2) Web project: *webApp_CarConfiguration/src/util/ConnectServer.java*(change serverIP)
- Intall and set the Tomcat Server in Eclipse, add the servlet-api.jar if necessary
- Run the Server project by executing *Server/src/driver/ServerDriver.java*
- Run the Client project by executing *Client/src/driver/ClientDriver.java*
- Upload the input files:
```
src/Ford_2009_Focus Wagon.properties
src/Ford_2003_Focus Wagon ZTW.properties
src/FW_ZTW_B.txt
```
- Run the AutoServlet.java in Tomcat Server