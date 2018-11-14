<%--
  Created by IntelliJ IDEA.
  User: Lev
  Date: 11/13/2018
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}" /></td>
            <td><c:out value="${user.getName()}" /></td>
            <td><c:out value="${user.getPassword()}" /></td>
            <td><c:out value="${user.getLogin()}" /></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
