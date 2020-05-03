<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>



<h3>Users List</h3>

<form method="POST" >
    <table border="0">
        <tr>
            <td>login</td>
            <td><input type="text" name="login" value="${user.login}" /></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="text" name="password" value="${user.password}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>