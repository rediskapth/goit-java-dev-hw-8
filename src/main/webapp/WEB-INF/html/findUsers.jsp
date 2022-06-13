<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
        <h2 class>Show all users</h2>
        <span style="color:red">${message}</span><br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Email</td>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Role</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <c:out value="${user.email}"/>
                            </td>
                            <td>
                                <c:out value="${user.firstName}"/>
                            </td>
                            <td>
                                <c:out value="${user.lastName}"/>
                            </td>
                            <td>
                                <c:out value="${user.userRole}"/>
                            </td>
                            <td>
                                <a href="/users/form/update/${user.id}">
                                    <input type="submit" value="Update"/>
                                </a>

                                <a href="/users/delete/${user.id}">
                                    <input type="submit" value="Delete"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
