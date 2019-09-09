<%-- 
    Document   : view-order
    Created on : Aug 16, 2019, 1:02:34 AM
    Author     : shuichi0906
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../include/head.jsp"/>
    </head>
    <body>
        <jsp:include page="../include/header.jsp"/>
        <!--================Home Banner Area =================-->
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
                <div class="container">
                    <div class="banner_content text-center">
                        <h2>List All Your Orders</h2>
                        <div class="page_link">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <section class="checkout_area p_120">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <form action="${pageContext.request.contextPath}/user/search" 
                              method="post" class="form-inline" >
                            <div class="form-group">
                                <label style="margin-right: 10px; font-size: 30px; color: coral " >From </label>
                                <input type="date" name="fromDate" class="form-control"  pattern="yyyy-MM-dd"/>
                                <label style="margin-left: 10px; margin-right: 10px; font-size: 30px; color: coral">To </label>
                                <input type="date" name="toDate" class="form-control" pattern="yyyy-MM-dd"/>
                                <input type="submit" value="SEARCH" style="font-size: 25px;margin-left: 20px;height: 60px" class="btn btn-warning"/>
                            </div>
                        </form>
                        <br><br><br>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <tr>
                                    <th>Id</th>
                                    <th>Date Order</th>                                
                                    <th>Status</th>
                                    <th>Total Price</th>
                                    <th>Note</th>
                                    <th>Options</th>
                                </tr>
                                <c:if test="${orders != null || fn:length(orders)>0}">
                                    <c:forEach var="order" items="${orders}">  
                                        <tr>
                                            <td>${order.id}</td>
                                            <td>${order.orderDate}</td> 
                                            <td>${order.status}</td>
                                            <td>${order.totalPrice}</td>
                                            <td>${order.note}</td>
                                            <td>
                                                <button class="btn-success" 
                                                        onclick="location.href = '<c:url value="/user/view-detail/${order.id}"/>'">
                                                    View Detail</button>
                                            </td> 
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${orders == null || fn:length(orders)<=0}">
                                    <tr>
                                        <td style="coler:red"
                                            colspan="9">No records</td>
                                    </tr>
                                </c:if>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include/footerjQuery.jsp"/>
    </body>
</html>
