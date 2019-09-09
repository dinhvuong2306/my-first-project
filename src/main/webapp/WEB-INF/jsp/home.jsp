<%-- 
    Document   : home
    Created on : Jul 26, 2019, 9:58:10 AM
    Author     : shuichi0906
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>

        <!--================Home Banner Area =================-->
        <section class="home_banner_area">
            <div class="banner_inner d-flex align-items-center">
                <div class="container">
                    <div class="banner_content row">
                        <div class="col-lg-5">
                            <h3>HandBags <br />Collections!</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                            <a class="white_bg_btn" href="<c:url value="/category/0"/>">View Collection</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->

        <!--================Feature Product Area =================-->
        <section class="feature_product_area">
            <div class="main_box">
                <div class="container">
                    <div class="row hot_product_inner">
                        <div class="col-lg-6">
                            <div class="hot_p_item">
                                <img class="img-fluid" src="<c:url value="/resources/img/product/hot-product/hot-p-1.jpg"/>" alt="">
                                <div class="product_text">
                                    <h4 style="color: #d01d33">Hot Deals of <br />this Month</h4>
                                    <a  href="<c:url value="/category/0"/>">Shop Now</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="hot_p_item">
                                <img class="img-fluid" src="<c:url value="/resources/img/product/hot-product/hot-p-2.jpg"/>" alt="">
                                <div class="product_text">
                                    <h4 style="color: #d01d33">Hot Deals of <br />this Month</h4>
                                    <a href="<c:url value="/category/0"/>">Shop Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="feature_product_inner">
                        <div class="main_title">
                            <h2>The Cheapest Products</h2>
                            <p>Easy for you to buying.</p>
                        </div>
                        <div class="feature_p_slider owl-carousel">
                            <c:forEach var="product" items="${cheap}">
                                <div class="item">
                                    <div class="f_p_item">
                                        <c:if test="${product.image!=null && fn:length(product.image)>0}">
                                            <div class="f_p_img">
                                                <a href="<c:url value="/product-detail/${product.id}"/>"><img class="img-fluid" src="<c:url value="/resources/img/product/feature-product/${product.image[0].name}"/>" alt=""></a>
                                                <div class="p_icon">
                                                    <a href="#"><i class="lnr lnr-heart"></i></a>
                                                        <s:url var="add" value="/add-to-cart" >
                                                            <s:param value="${product.id}" name="productId"/>
                                                        </s:url>
                                                    <a href="${add}"><i class="lnr lnr-cart"></i></a>
                                                </div>
                                            </div>
                                        </c:if>
                                        <a href="<c:url value="/product-detail/${product.id}"/>"><h4>${product.name}</h4></a>
                                        <h5>${product.price}</h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Feature Product Area =================-->

        <!--================Deal Timer Area =================-->
        <section class="timer_area">
            <div class="container">
                <div class="main_title">
                    <h2>Exclusive Hot Deal Ends Soon!</h2>
                    <p>Who are in extremely love with eco friendly system.</p>
                    <a class="main_btn" href="<c:url value="/category/0"/>">Shop Now</a>
                </div>
                <div class="timer_inner">
                    <div id="timer" class="timer">
                        <div class="timer__section days">
                            <div class="timer__number"></div>
                            <div class="timer__label">days</div>
                        </div>
                        <div class="timer__section hours">
                            <div class="timer__number"></div>
                            <div class="timer__label">hours</div>
                        </div>
                        <div class="timer__section minutes">
                            <div class="timer__number"></div>
                            <div class="timer__label">Minutes</div>
                        </div>
                        <div class="timer__section seconds">
                            <div class="timer__number"></div>
                            <div class="timer__label">seconds</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Deal Timer Area =================-->
        <!--================Latest Product Area =================-->
        <section class="feature_product_area latest_product_area">
            <div class="main_box">
                <div class="container">
                    <div class="feature_product_inner">
                        <div class="main_title">
                            <h2>Latest Products</h2>
                            <p>Who are in extremely love with eco friendly system.</p>
                        </div>
                        <div class="latest_product_inner row">
                            <c:forEach var="product" items="${products}">
                                <div class="col-lg-3 col-md-4 col-sm-6">
                                    <div class="f_p_item">
                                        <c:if test="${product.image!=null && fn:length(product.image)>0}">
                                            <div class="f_p_img">
                                                <a href="<c:url value="/product-detail/${product.id}"/>"><img class="img-fluid" src="<c:url value="/resources/img/product/feature-product/${product.image[0].name}"/>" alt=""></a>
                                                <div class="p_icon">
                                                    <a href="#"><i class="lnr lnr-heart"></i></a>
                                                        <s:url var="add" value="/add-to-cart" >
                                                            <s:param value="${product.id}" name="productId"/>
                                                        </s:url>
                                                    <a href="${add}"><i class="lnr lnr-cart"></i></a>
                                                </div>
                                            </div>
                                        </c:if>
                                        <a href="<c:url value="/product-detail/${product.id}"/>"><h4>${product.name}</h4></a>
                                        <h5>${product.price}</h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Latest Product Area =================-->
        <!--================Clients Logo Area =================-->
        <section class="clients_logo_area">
            <div class="container">
                <div class="main_title">
                    <h2>Top Brands of this Month</h2>
                    <p>Who are in extremely love with eco friendly system.</p>
                </div>
                <div class="clients_slider owl-carousel">
                    <div class="item">
                        <img src="<c:url value="/resources/img/clients-logo/c-logo-1.png"/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/resources/img/clients-logo/c-logo-2.png"/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/resources/img/clients-logo/c-logo-3.png"/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/resources/img/clients-logo/c-logo-4.png"/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/resources/img/clients-logo/c-logo-5.png"/>" alt="">
                    </div>
                </div>
            </div>
        </section>
        <!--================End Clients Logo Area =================-->
        <jsp:include page="include/footerjQuery.jsp"/>
    </body>
</html>
