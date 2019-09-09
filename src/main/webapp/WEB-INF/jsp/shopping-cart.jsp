<%-- 
    Document   : cart
    Created on : Jul 29, 2019, 9:37:43 PM
    Author     : shuichi0906
--%>

<%@taglib  uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                        <h2>Shopping Cart</h2>
                        <div class="page_link">
                            <a href="<c:url value="/home"/>">Home</a>
                        </div>
                        <h2>Order Date: <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></h2>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        <!--================Cart Area =================-->
        <section class="cart_area">
            <div class="container">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="od" items="${order.orderDetail}">
                                    <tr>
                                        <td>
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img height="100" src="<c:url value="/resources/img/product/feature-product/${od.product.image[0].name}"/>" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <a href="<c:url value="/product-detail/${od.product.id}"/>"><p>${od.product.name}</p></a>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <h5>${od.price} VNĐ</h5>
                                        </td>

                                        <td>
                                            <form action="<c:url value="/update-quantity/${od.product.id}"/>" method="post">
                                                <div class="product_count" >
                                                    <input type="number" min="1" name="quantity"  max="${od.product.quantityInStory}" 
                                                           value="${od.quantity}" title="Quantity:" class="input-text qty">
                                                    <button type="submit">Update</button>
                                                </div>
                                            </form>
                                        </td>
                                        <td>
                                            <h5><button class="btn btn-danger btn-sm"   type="button" onclick='location.href = "<c:url value="/delete/${od.product.id}"/> "'  >DELETE
                                                </button></h5>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
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
                                
                                <tr class="out_button_area">
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="checkout_btn_inner">
                                            <a class="gray_btn" href="<c:url value="/continue"/>">Continue Shopping</a>
                                            <a class="main_btn" href="<c:url value="/view-checkout"/>">Proceed to checkout</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->

        <jsp:include page="include/footerjQuery.jsp"/>
    </body>
</html>
