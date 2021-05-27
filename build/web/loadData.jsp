<%-- 
    Document   : loadData
    Created on : May 17, 2021, 3:17:03 PM
    Author     : MinhT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Source Share</title>
    </head>
    <body>
        <h1 ${sessionScope.acc!= null}> hello ${sessionScope.acc.name}</h1> 
        <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <a class="fa fa-search" href="LoadSourceControll"> Home</a>
                        </button>
         </div>
        <c:if test='${sessionScope.acc.roleID==1}'>
           <h1 style="color: green">${requestScope.bookingSuccess}</h1>
           <h1 style="color: fail">${requestScope.bookingFail}</h1>
            <form action="MainController?btnAction=search&index=1" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                  
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search">tìm kiếm</i>
                        </button>
                    </div>
                    <div>
                        <c:forEach begin="1" end="${endPage}" var="i">
                            <a href="SearchController?index=${i}&txt=${txt}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </form>
            <form method="Post" action="MainController?btnAction=Viewlist">
            <input type="submit" value="View List Booking" name="btnAction" />
            </form>
            <table border="1px solid black" action="btnAction">
                <tr>
                    <th>productID</th>
                    <th>productName</th>
                    <th>color</th>
                    <th>quanlity</th>
                    <th>Category Name</th>
                    <th>Create Date</th>
                    <th>funtion<th>
                </tr>
                <c:forEach items="${src}" var="x" varStatus="1">
                    <tr>
                        <td name="id"> ${x.productID}</td>
                        <td>${x.productName}</td>
                        <td>
                            ${x.color}
                        </td>
                        <td>
                            ${x.categoryName}
                        </td>
                        <td>
                            ${x.quanlity}
                        </td>
                         <td>
                            ${x.createdate}
                        </td>
                        <td>
                                <form method="POST" action="MainController?btnAtion=Booking&id=${x.getProductID()}">
                                    <input type="hidden" name="productID" value="${x.getProductID()}" />
                                    <input type="submit" name="btnAction" value="Booking" />
                                </form>
                         </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="Update.jsp">accept</a>
            <a href="MainController?btnAction=logout">LogOut</a>

        </c:if>

        <c:if test = "${sessionScope.acc.roleID==2}">
            <table border="1px solid black" action="btnAction">
                <tr>
                    <th>request ID</th>
                    <th>dateBook</th>
                    <th>statusReqID</th>
                    <th>email</th>
                    <th>productID</th>
                </tr>
                <c:forEach items="${listReq}" var="x">
                    <tr>
                        <td name="id">${x.requestID}</td>
                        <td>${x.dateBook}</td>
                        <td>
                            ${x.statusReqID}
                        </td>
                        <td>${x.email}</td>
                        <td>
                            ${x.productID}
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="Update.jsp">Rent Source</a>
            <a href="MainController?btnAction=logout">LogOut</a>
        </c:if>
    </body>
</html>