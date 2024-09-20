<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h1>Welcome to the Dashboard!</h1>
<%
    String userRole = (String) session.getAttribute("userRole");
    if (userRole != null) {
        out.println("<h2>Logged in as: " + userRole + "</h2>");
    } else {
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>
