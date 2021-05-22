<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
</head>
<body>
<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

    h2 {
        font-weight: bold;
        color: #FFFF;
        text-align: center;
        vertical-align: middle;
        font-size:3.9vw;
    }
    .add_user {
        width: 860px;
        margin: auto;
        font-size:1.3vw;

    }
    input,select {
        font-family: "Roboto", sans-serif;
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 15px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }
    button {
        font-family: "Roboto", sans-serif;
        text-transform: uppercase;
        outline: 0;
        background: #4CAF50;
        width: 100%;
        border: 0;
        padding: 15px;
        color: #FFFFFF;
        font-size: 14px;
        cursor: pointer;
    }
    .form button:hover,.form button:active,.form button:focus {
        background: #43A047;
    }

    body {
        background: #76b852;
        background: -webkit-linear-gradient(right, #76b852, #8DC26F);
        background: -moz-linear-gradient(right, #76b852, #8DC26F);
        background: -o-linear-gradient(right, #76b852, #8DC26F);
        background: linear-gradient(to left, #76b852, #8DC26F);
        font-family: "Roboto", sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }
</style>
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
