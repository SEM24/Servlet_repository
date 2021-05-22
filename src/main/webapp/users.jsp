<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

    h2 {
        font-weight: bold;
        color: #FFFF;
        text-align: center;
        vertical-align: middle;
        line-height: 100%;
        height: 100%;
        text-decoration: none;
        font-size:3.9vw;
    }
    .user_list {
        width: 860px;
        margin: auto;
        font-size:1.3vw;

    }
    table, th, td {
        border: 1px solid black;
        font-weight: bold

    }

    table {
        width: 100%;
        background-color:#ffffff

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
    .form .message {
        margin: 15px 0 0;
        color: #FFFF;
        font-size: 20px;
        font-weight: bold
    }
</style>

<h2>User List</h2>
<div class="user_list">
    <div class="form">
        <table>
            <tr>
                <th>Login</th>
                <th>Role</th>
                <c:if test="${access_admin == user_role or access_support == user_role}">
                    <th>Action</th>
                </c:if>
            </tr>
            <c:forEach var = "user" items = "${users}">
                <tr>
                    <td><c:out value = "${user.key}"/></td>
                    <td><c:out value = "${user.value.role}"/></td>
                    <c:if test="${access_admin == user_role or access_support == user_role}">
                      <td>
                          <form action="/users" method="post">
                              <input type="hidden" name="delete-user" value="${user.key}"/>
                              <input type="submit" value="Delete"/>
                          </form>
                      </td>
                    </c:if>
                </tr>
            </c:forEach>

        </table>
    <c:if test="${access_admin == user_role or access_support == user_role}">
        <p class="message">Add new user: <a href="/addForm">Add new user</a></p>
    </c:if>
        <p class="message">Logout: <a href="/logout">Logout</a></p>
    </div>
</div>
</body>
</html>
