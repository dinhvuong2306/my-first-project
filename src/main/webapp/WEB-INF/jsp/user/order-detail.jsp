<%-- 
    Document   : order-detail
    Created on : Aug 16, 2019, 11:00:59 PM
    Author     : shuichi0906
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <h2>View Your Orders Detail </h2>
                        <div class="page_link">
                            <a href="<c:url value="/user/view-list-order"/>">Back to your history orders</a>
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
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <tr>
                                    <th>Id</th>
                                    <th>Product Name</th>                                
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                                <c:forEach var="od" items="${order.orderDetail}">  
                                    <tr>
                                        <td>${od.id}</td>
                                        <td>${od.product.name}</td> 
                                        <td>${od.price}</td> 
                                        <td>${od.quantity}</td>
                                        <td>${od.price * od.quantity}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>

                                    </td>
                                    <td>

                                    </td>                                
                                    <td> 

                                    </td>
                                    <td>
                                        <h5>Subtotal</h5>
                                    </td>
                                    <td>
                                        <h5>${order.totalPrice} VNĐ</h5>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include/footerjQuery.jsp"/>
    </body>
</html>
