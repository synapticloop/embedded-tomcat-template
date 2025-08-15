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

<p>This file is designed as a <em>welcome file</em>, so you do not need to include
the <code>index.jsp</code> in the URL. <em>(In fact the filter will not allow hitting
JSPs directly)</em></p>

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