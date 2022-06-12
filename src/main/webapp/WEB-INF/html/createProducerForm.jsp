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
            <form:form action="/producers/" method="post" modelAttribute="producerDto">
            <h2 class>Create new producer</h2>
                <div class="form-group">

                    <spring:bind path="name">Producer Name: <span style="color:red">${message}</span><br>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="name" class="form-control" placeholder="Enter Producer Name"></form:input>
                            <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>

                </div>
                    <a href="/producers/findProducers"><input type="button" value="Back"></a>
                    <input type="submit" value="Submit"/>
            </form:form>

        </div>
    </body>
</html>
