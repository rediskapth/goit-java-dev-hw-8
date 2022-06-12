<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

           <nav class="navbar navbar-inverse">
              <div class="container">
                <div class="navbar-header">
                  <a class="navbar-brand" href="/">Some shop</a>
                </div>
                <ul class="nav navbar-nav">
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Producers <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/producers/findProducers">Find All Producers</a></li>
                      <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/producers/form/create">Create New Producer</a></li>
                      </security:authorize>
                    </ul>
                  </li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Products <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/products/findProducts">Find All Products</a></li>
                      <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/products/form/create">Create New Product</a></li>
                      </security:authorize>
                    </ul>
                  </li>
                  <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                          <li><a href="/users/findUsers">Find All Users</a></li>
                          <li><a href="/users/form/create">Create New User</a></li>
                      </ul>
                    </li>
                  </security:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a style="float: right" href="/logout">Logout</a>
                    </li>
                </ul>
              </div>
            </nav>