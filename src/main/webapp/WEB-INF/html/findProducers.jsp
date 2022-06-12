<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
        <h2 class>Show all producers</h2>
        <span style="color:red">${message}</span><br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Name</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${producers}" var="producer">
                        <tr>
                            <td>
                                <c:out value="${producer.name}"/>
                            </td>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                            <td>
                                <a href="/producers/form/update/${producer.id}">
                                    <input type="submit" value="Update"/>
                                </a>

                                <a href="/producers/delete?id=${producer.id}">
                                    <input type="submit" value="Delete"/>
                                </a>
                            </td>
                    </security:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
