<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title><c:out value="${pageTitle}" /></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--================================================================================================-->
        <link rel="icon" type="image/png" href="assets/images/icons/favicon.png" />
        <!-- ===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
        <!--================================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!-- ===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/linearicons-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css"><!--
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/MagnificPopup/magnific-popup.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/css/util.css">
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
        <!--<link rel="stylesheet" type="text/css" href="assets/css/style.css">-->
        <!--===============================================================================================-->
    </head>

    <body class="animsition">

        <!-- Header -->
        <header>
            <!-- Header desktop -->
            <div class="container-menu-desktop">
                <div class="wrap-menu-desktop">
                    <nav class="limiter-menu-desktop container">
                        <!-- Logo desktop -->
                        <a href="HomeServlet" class="logo">
                            <img src="assets/images/icons/logo-01.png" alt="IMG-LOGO">
                        </a>

                        <!-- Menu desktop -->
                        <div class="menu-desktop">
                            <ul class="main-menu">
                                <li class="active-menu">
                                    <a href="HomeServlet">Home</a>
                                </li>

                                <li class="active-menu">
                                    <a href="ShopServlet">Shop</a>
                                </li> 

                                <li class="active-menu">
                                    <a href="CategoryServlet?categoryId=2">Categories</a>
                                    <ul class="sub-menu">
                                        <c:forEach items="${categoryList}" var="category">
                                            <li class="menu-item">
                                                <a href="CategoryServlet?categoryId=${category.id}">${category.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>

                                <li class="active-menu">
                                    <a href="BlogServlet">Blog</a>
                                </li>

                                <li class="active-menu">
                                    <a href="AboutServlet">About</a>
                                </li>

                                <li class="active-menu">
                                    <a href="ContactServlet">Contact</a>
                                </li>
                            </ul>
                        </div>

                        <!-- Icon header -->
                        <div class="wrap-icon-header flex-w flex-r-m">          
                            <a href="CartServlet" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="2">
                                <i class="zmdi zmdi-shopping-cart"></i>
                            </a>


                            <a href=""
                               class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
                               data-notify="0">
                                <i class="zmdi zmdi-favorite-outline"></i>
                            </a>

                            <%@ page session="true" %>
                            <% if (session.getAttribute("user") != null) { 
                                Object user = session.getAttribute("user");
                                String email = "User";
                                try {
                                    java.lang.reflect.Method getEmailMethod = user.getClass().getMethod("getEmail");
                                    if (getEmailMethod != null) {
                                        Object result = getEmailMethod.invoke(user);
                                        if (result != null) {
                                            email = result.toString();
                                        }
                                    }
                                } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {}
                            %>
                            <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 d-flex align-items-center">
                                <span class="mr-2"><%= email %></span>
                                <a href="LogoutServlet" class="icon-header-item cl2 hov-cl1 trans-04">
                                    <i class="zmdi zmdi-power"></i>
                                </a>
                            </div>
                            <% } else { %>
                            <a href="LoginServlet" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11">
                                <i class="zmdi zmdi-account"></i>
                            </a>
                            <% } %>
                        </div>
                    </nav>
                </div>
            </div>
        </header>