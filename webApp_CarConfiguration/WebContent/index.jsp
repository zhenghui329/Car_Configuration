<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Configuration Completed</title>
<link rel=stylesheet href="style.css" type="text/css">
</head>

<%@ page import="model.*,coreservlets.*"%>
<%
	String msg;
	String msg2;
	// get the parameters
	String modelName = (String) request.getSession().getAttribute("modelName");
	String optSetName = (String) request.getSession().getAttribute("optSetName");
	if (modelName == null) {
		modelName = "";
	}
	if (optSetName == null) {
		optSetName = "";
	}
	String nameList[] = optSetName.split(",");
	StringBuilder select = new StringBuilder().append(modelName + ',');
	for (int i = 0; i < nameList.length; i++) {
		select.append(request.getParameter(nameList[i]) + ',');
	}
	// send parameters to the server
	// it returns a configured auto
	ConfigureServlet c_servlet = new ConfigureServlet();
	Object obj = c_servlet.getObjectFromServer(5, select.toString());
	Automobile a;
	if (obj instanceof Automobile) {
		a = (Automobile) obj;
		msg = "Your Car is completed!";
		msg2 = "Here is what you selected:";
	} else {
		a = new Automobile("", 0, "", 0, 0);
		msg = "Sorry! You entered a wrong page";
		msg2 = "";
	}
	request.getSession().setAttribute("auto", a);
	int i = 0;
%>


<body>
	<div class="main">
		<div class="header">
			<h1><%=msg%></h1>
		</div>
		<div class="middle">
			<h4><%=msg2%></h4>
			<table>
				<thead>
					<tr>
						<th><%=a.getName()%></th>
						<th>Base Price</th>
						<th><%=a.getBasePrice()%></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=a.getOptionSetName(i)%></td>
						<td><%=a.getOptionChoice(a.getOptionSetName(i))%></td>
						<td><%=a.getOptionChoicePrice(a.getOptionSetName(i++))%></td>
					</tr>
					<tr>
						<td><%=a.getOptionSetName(i)%></td>
						<td><%=a.getOptionChoice(a.getOptionSetName(i))%></td>
						<td><%=a.getOptionChoicePrice(a.getOptionSetName(i++))%></td>
					</tr>
					<tr>
						<td><%=a.getOptionSetName(i)%></td>
						<td><%=a.getOptionChoice(a.getOptionSetName(i))%></td>
						<td><%=a.getOptionChoicePrice(a.getOptionSetName(i++))%></td>
					</tr>
					<tr>
						<td><%=a.getOptionSetName(i)%></td>
						<td><%=a.getOptionChoice(a.getOptionSetName(i))%></td>
						<td><%=a.getOptionChoicePrice(a.getOptionSetName(i++))%></td>
					</tr>
					<tr>
						<td><%=a.getOptionSetName(i)%></td>
						<td><%=a.getOptionChoice(a.getOptionSetName(i))%></td>
						<td><%=a.getOptionChoicePrice(a.getOptionSetName(i++))%></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>Total Price</td>
						<td></td>
						<td>$<%=a.getTotalPrice()%></td>
					</tr>
				</tfoot>
			</table>
			<form id="form3" method="get" action="configuration_excel.jsp">
				<input name="format" value="excel" type="hidden">
				<button type="submit" form="form3">download as excel</button>
			</form>
		</div>
		<footer>2019 Spring 35B Lab6</footer>
	</div>
</body>
</html>