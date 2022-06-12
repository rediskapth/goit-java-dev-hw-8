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
        <h2 class>Show all products</h2>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Producer</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td>
                                <c:out value="${product.name}"/>
                            </td>
                            <td>
                                <c:out value="${product.price}"/>
                            </td>
                            <td>
                                <c:out value="${product.producer.name}"/>
                            </td>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                            <td>
                                <a href="/products/form/update/${product.id}">
                                    <input type="submit" value="Update"/>
                                </a>

                                <a href="/products/delete?id=${product.id}">
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
