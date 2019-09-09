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
                        <h2>Product Checkout</h2>
                        <div class="page_link">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Checkout Area =================-->
        <section class="checkout_area p_120">
            <div class="container">
                <div class="returning_customer">
                    <div class="check_title">
                        <h2>Returning Customer? <a href="<c:url value="/login"/>">Click here to login</a></h2>
                    </div>
                </div>
                <div class="billing_details">
                    <div class="row">
                        <div class="col-lg-6" class="row contact_form">
                            <h3>Billing Details</h3>
                            <form:form  action="${pageContext.request.contextPath}/proceed" method="post"
                                        modelAttribute="shipping"  >
                                <div class="col-md-12 form-group p_star">
                                    <input required type="text" class="form-control" id="first" 
                                           value="${shipping.fullName}" name="fullName" placeholder="Your Full Name"> 
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required type="text" class="form-control" id="number" 
                                           value="${shipping.phoneNumber}" name="phoneNumber" placeholder="Phone Number">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input type="text" class="form-control" id="email" 
                                           required value="${shipping.email}" name="email" placeholder="Email Address">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <label>Your BirthDate</label>
                                    <input type="date" class="form-control" value="${shipping.birthDate}" name="birthDate">
                                </div>
                                <div class="col-md-12 form-group p_star">
                                    <input required type="text" class="form-control" id="add1" 
                                           value="${shipping.address}" name="address" placeholder="Ship to">
                                </div>
                                <div class="col-md-12 form-group">
                                    		<textarea class="form-control" name="note" id="message" rows="1" placeholder="Order Notes"></textarea>
                                </div>
                                <div class="col-sm-12" style="text-align: center">
                                    <button type="submit" class="main_btn">PROCEED TO PAYPAL </button>
                                </div>
                            </form:form>

                        </div>
                        <div class="col-lg-6">
                            <div class="order_box">
                                <h2>Your Order</h2>
                                <ul class="list">
                                    <li><a href="#">Product <span> Total</span></a></li>
                                        <c:forEach var="od" items="${order.orderDetail}">
                                        <li><a href="#">${od.product.name} <span class="middle">x ${od.quantity}</span> <span class="last">${od.price * od.quantity} VNĐ</span></a></li>
                                        </c:forEach>
                                </ul>
                                <ul class="list list_2">
                                    <li><a href="#">Subtotal <span>${order.totalPrice} VNĐ</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Checkout Area =================-->

        <jsp:include page="include/footerjQuery.jsp"/>
    </body>
</html>
