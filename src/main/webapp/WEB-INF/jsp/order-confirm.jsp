<%-- 
    Document   : checkout
    Created on : Aug 6, 2019, 7:41:29 PM
    Author     : shuichi0906
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>

        <!--================Home Banner Area =================-->
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
                <div class="container">
                    <div class="banner_content text-center">
                        <h2>Order Confirmation</h2>
                        <div class="page_link">
                            <a href="<c:url value="/home"/>">Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Order Details Area =================-->
        <section class="order_details p_120">
            <div class="container">
                <h3 class="title_confirmation">Thank you. Your order has been received.</h3>
                <div class="row order_d_inner">
                    <div class="col-lg-4">
                        <div class="details_item">
                            <h4>Order Info</h4>
                            <ul class="list">
                                <li><a href="#"><span>Order number</span> : ${order.id}</a></li>
                                <li><a href="#"><span>Date</span> : ${order.orderDate}</a></li>
                                <li><a href="#"><span>Total</span> : ${order.totalPrice} VNĐ</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="details_item">
                            <h4>Shipping Address</h4>
                            <ul class="list">
                                <li><a href="#"><span>Street</span> : ${order.shipping.address}</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="details_item">
                            <h4>Shipping Info</h4>
                            <ul class="list">
                                <li><a href="#"><span>Customer</span> : ${order.shipping.fullName}</a></li>
                                <li><a href="#"><span>Phone Number</span> : ${order.shipping.phoneNumber}</a></li>
                                <li><a href="#"><span>Customer Id Card</span> : ${order.shipping.idCard}</a></li>
                                <li><a href="#"><span>Email </span> : ${order.shipping.email}</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="order_details_table">
                    <h2>Order Details</h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="od" items="${order.orderDetail}">
                                    <tr>
                                        <td>
                                            <p>${od.product.name}</p>
                                        </td>
                                        <td>
                                            <h5>x ${od.quantity}</h5>
                                        </td>
                                        <td>
                                            <p>${od.price * od.quantity}</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <h4>Subtotal</h4>
                                    </td>
                                    <td>
                                        <h5></h5>
                                    </td>
                                    <td>
                                        <p>${order.totalPrice}</p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Order Details Area =================-->

        <jsp:include page="include/footerjQuery.jsp"/>
    </body>
</html>
