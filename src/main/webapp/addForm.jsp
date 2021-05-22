<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
</head>
<body>
<form action="addForm" method="post">
<h2>Add user</h2>
<div class="add_user">
    <div class="form">
        <form class="add_form">
            <input type="text" name="login" placeholder="login"/>
            <input type="password" name="password" placeholder="password"/>

            <select name="role">
                <c:forEach var="roleItem" items='<%= request.getSession().getServletContext().getAttribute("roleList") %>'>
                    <option value='<c:out value="${roleItem}" />'><c:out value="${roleItem}"/></option>
                </c:forEach>
            </select>
            <button type="submit">Submit</button>
            <p class="message">User list: <a href="/users">User list</a></p>
            <p class="message">Logout: <a href="/logout">Logout</a></p>
        </form>
    </div>
</div>
</form>
</body>
</html>
