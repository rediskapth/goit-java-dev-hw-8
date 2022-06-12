<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
            <form:form action="/products/" method="post" modelAttribute="productDto">
            <h2 class>Create new product</h2>
                <div class="form-group">

                    <spring:bind path="name">Product Name:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="name" class="form-control" placeholder="Enter Product Name"></form:input>
                            <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="price">Product Price:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="number" path="price" class="form-control" placeholder="Enter Product Number"></form:input>
                            <form:errors path="price"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="producer">Choose Producer:
                        <select class="form-control" id="producer" name="producer">
                            <c:forEach items="${producers}" var="producer">
                                <option value="${producer}">
                                    <c:out value="${producer.name}"/><br>
                                </option>
                            </c:forEach>
                         </select>
                    </spring:bind>

                </div>
                    <a href="/products/findProducts"><input type="button" value="Back"></a>
                    <input type="submit" value="Submit"/>
            </form:form>

        </div>
    </body>
</html>
