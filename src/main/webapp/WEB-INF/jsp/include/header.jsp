
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header_area">
    <div class="top_menu row m0">
        <div class="container">
            <div class="float-left">
                <a href="mailto:gladiator0906@gmail.com">support NuHaShop</a>
            </div>
            <div class="float-right">
                <ul class="header_social">
                    <li><a href="https://www.facebook.com/NuHachatluongtaothuonghieu/"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                    <li><a href="#"><i class="fa fa-behance"></i></a></li>
                </ul>
            </div>
        </div>	
    </div>	
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light main_box">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="<c:url value="/home"/>"><img src="<c:url value="/resources/img/logo_1.png"/>" alt=""></a>
                <h6>${account.email}</h6>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <li class="nav-item active"><a class="nav-link" href="<c:url value="/home"/>">Home</a></li> 
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="<c:url value="/category/0"/>">Shop Category</a>
                                <li class="nav-item"><a class="nav-link" href="<c:url value="/view_shopping_cart"/>">Shopping Cart</a></li>
                            </ul>
                        </li> 
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
                                <li class="nav-item"><a class="nav-link" href="single-blog.html">Blog Details</a></li>
                            </ul>
                        </li> 
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="<c:url value="/login"/>">Login</a>
                                <li class="nav-item"><a class="nav-link" href="<c:url value="/user/view-list-order"/>">View Order History</a>
                                <li class="nav-item"><a class="nav-link" href="<c:url value="/user/changeAccountInfo"/>">Edit Account</a></li>
                            </ul>
                        </li> 
                        <li class="nav-item"><a class="nav-link" href="https://www.facebook.com/NuHachatluongtaothuonghieu/">Contact</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a href="<c:url value="/view_shopping_cart"/>" class="cart"><i class="lnr lnr lnr-cart"></i></a></li>
                        <li class="nav-item">
                            <div class="search" style="text-align: right">
                                <form action="${pageContext.request.contextPath}/search" 
                                      method="post" class="form-inline">
                                    <div class="">
                                        <input name="searchTxt" class="form-control" />
                                        <button  class="lnr lnr-magnifier" aria-hidden="true"/>
                                    </div>
                                </form>
                            </div>
                            
                        </li>
                    </ul>
                </div> 
            </div>
        </nav>
    </div>
</header>