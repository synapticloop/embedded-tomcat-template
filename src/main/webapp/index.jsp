<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page import="java.util.*" %>
<%
	Random random = new Random(System.currentTimeMillis());
	int randomNum = random.nextInt(5);
	pageContext.setAttribute("randomNumber", randomNum);
%>
<html lang="en">
<body>
<h1>Welcome JSP</h1>
<p>Random Number ${randomNumber}</p>
<c:choose>
	<c:when test="${randomNumber == 3}">
		<p>Lucky number 3</p>
	</c:when>
	<c:otherwise>
		<p>This is not the lucky number 3</p>
	</c:otherwise>
</c:choose>
</body>
</html>