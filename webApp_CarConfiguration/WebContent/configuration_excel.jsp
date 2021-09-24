<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Download excel for configuration</title>
</head>

<%@ page import="model.*" %>

<%	
String format = request.getParameter("format");
if ((format != null) && (format.equals("excel"))) {
	response.setContentType("application/vnd.ms-excel");
}
Automobile a = (Automobile)request.getSession().getAttribute("auto");
int i = 0;
%>


<body>
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
</body>
</html>