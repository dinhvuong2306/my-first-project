<%-- 
    Document   : change-account-info
    Created on : Aug 22, 2019, 11:50:30 PM
    Author     : shuichi0906
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../include/head.jsp"/>
        <style>
            .ui-error{
                color: red;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../include/header.jsp"/>
        <!--================Home Banner Area =================-->
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
                <div class="container">
                    <div class="banner_content text-center">
                        <h2>Change Your Account Information</h2>
                        <div class="page_link">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        <section class="checkout_area p_120">
            <div class="container">
                <c:if test="${message!=null && message!=''}">
                    <div class="row">
                        <div class="col-sm-12" style="text-align: center">
                            <c:if test="${status!=null && status=='success'}">
                                <div class="alert alert-success">
                                    ${message}
                                </div>
                            </c:if>
                            <c:if test="${status!=null && status=='fail'}">
                                <div class="alert alert-danger">
                                    ${message}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-sm-12">
                        <form:form action="${pageContext.request.contextPath}/user/change-info" method="post" 
                                   modelAttribute="account" class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-2">Your Email
                                    <span class="ui-error">(*)</span></label>
                                <div class="col-sm-8">
                                    <input name="email" class="form-control" required
                                           value="${account.email}"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2">
                                    Password <span class="ui-error">(*)</span></label>
                                <div class="col-sm-8">
                                    <input type="password" name="password" required
                                           class="form-control" value="${account.password}"/>
                                </div>
                            </div>

                            <div class="form-group" style="text-align: center">
                                <button class="btn btn-primary" 
                                        type="submit">Change</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include/footerjQuery.jsp"/>
    </body>
</html>
