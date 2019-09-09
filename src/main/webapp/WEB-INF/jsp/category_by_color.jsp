<%-- 
    Document   : category
    Created on : Jul 27, 2019, 12:19:08 AM
    Author     : shuichi0906
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
                <div class="container">
                    <div class="banner_content text-center">
                        <h2>Shop Category Page</h2>
                        <div class="page_link">
                            <a href="<c:url value="/home"/>">Home</a>
                            <a href="<c:url value="/category/0"/>">Category</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        <!--================Category Product Area =================-->
        <section class="cat_product_area p_120">
            <div class="container">
                <div class="row flex-row-reverse">
                    <div class="col-lg-9">
                        <div class="product_top_bar">
                            <div class="right_page ml-auto">
                                <nav class="cat_page" aria-label="Page navigation example">
                                    <ul class="pagination">                                        
                                        <li class="page-item"><a class="page-link" href="#"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a></li>
                                        <c:forEach var="i" begin="1" end="${totalPage}">

                                            <c:if test="${colorId!=0}">
                                                <li class="page-item active">
                                                    <a class="page-link" href="<c:url value="/color/${colorId}/${i-1}"/>">
                                                        <c:out value="${i}"/>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${colorId==0}">
                                                <li class="page-item active">
                                                    <a class="page-link" href="<c:url value="/color/${i-1}"/>">
                                                        <c:out value="${i}"/>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <li class="page-item"><a class="page-link" href="#"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a></li>                                        
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="latest_product_inner row">
                            <c:forEach var="product" items="${products}">
                                <div class="col-lg-3 col-md-4 col-sm-6">
                                    <div class="f_p_item">
                                        <c:if test="${product.image!=null && fn:length(product.image)>0}">
                                            <div class="f_p_img">
                                                <img class="img-fluid" src="<c:url value="/resources/img/product/feature-product/${product.image[0].name}"/>" alt="">
                                                <div class="p_icon">
                                                    <a href="#"><i class="lnr lnr-heart"></i></a>
                                                        <s:url var="add" value="/add-to-cart" >
                                                            <s:param value="${product.id}" name="productId"/>
                                                        </s:url>
                                                    <a href="${add}" ><i class="lnr lnr-cart"></i></a>
                                                </div>
                                            </div>
                                        </c:if>
                                        <a href="<c:url value="/product-detail/${product.id}"/>"><h4>${product.name}</h4></a>
                                        <h5>${product.price} VNĐ</h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="left_sidebar_area">
                            <aside class="left_widgets cat_widgets">
                                <div class="l_w_title">
                                    <h3>Danh mục sản phẩm</h3>
                                </div>
                                <div class="widgets_inner">
                                    <ul class="list">
                                        <c:forEach var="category" items="${categories}">
                                            <li><a href="<c:url value="/category/${category.id}/0"/>">${category.name}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </aside>
                            <aside class="left_widgets p_filter_widgets">
                                <div class="l_w_title">
                                    <h3>PRODUCT FILTERS</h3>
                                </div>
                                <div class="widgets_inner">
                                    <h4>Brands</h4>
                                    <ul class="list">
                                        <c:forEach var="brand" items="${brands}">
                                            <li><a href="<c:url value="/brand/${brand.id}/0"/>">${brand.name}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                                <div class="widgets_inner">
                                    <h4>Color</h4>
                                    <ul class="list">
                                        <c:forEach var="color" items="${colors}">
                                            <li><a href="<c:url value="/color/${color.id}/0"/>">${color.name}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </aside>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Category Product Area =================-->
        <jsp:include page="include/footerjQuery.jsp"/>
    </body>
</html>
