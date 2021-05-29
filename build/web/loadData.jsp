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
        <form name="v" action="MainController" method="POST">
            <c:if test='${sessionScope.acc.roleID == 2}'>
                <h1 style="color: green">${requestScope.successConfirm}</h1>
                <h1 style="color: green">${requestScope.deleteConfirm}</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>DateBook</th>
                            <th>email</th>
                            <th>productName</th>
                            <th>status</th>
                            <th>Accept</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${requestScope.arrayListRequest}">
                            <tr>
                                <td>${item.dateBook}</td>
                                <td>${item.email}</td>
                                <c:forEach var="a" items="${requestScope.listResource}">
                                    <c:if test="${item.productID eq a.productID}">
                                        <td>${a.productName}</td>   
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="i" items="${requestScope.listStatusRequest}">
                                    <c:if test="${item.statusReqID eq i.statusReqID}">
                                        <td>${i.statusReqName}</td>
                                    </c:if>
                                </c:forEach>
                        <form method="Post" action="MainController?btnAction=Accept&requestID=${item.requestID}" >
                            <td>
                                <input type="hidden" name="productID" value="${item.productID}" />
                                <input type="hidden" name="flag" value="true" />
                                <input type="hidden" name="requestID" value="${item.requestID}" />
                                <c:if test="${item.statusReqID ne 4}">
                                    <input type="submit" value="Accept" name="btnAction" />
                                </c:if>
                            </td>
                        </form>
                        <form method="Post" action="MainController?btnAction=deleteReqs&requestID=${item.requestID}">
                            <td>
                                <input type="hidden" name="productID" value="${item.productID}" />
                                <input type="hidden" name="flag" value="false" />
                                <input type="hidden" name="requestID" value="${item.requestID}" />
                                <c:if test="${item.statusReqID ne 4}">
                                    <input type="submit" value="Delete" name="btnAction" />
                                </c:if>
                            </td>
                        </form>
                        </tr>    
                    </c:forEach>
                    <c:if test="${empty requestScope.arrayListRequest}">
                        <h1>List Request Empty</h1>
                    </c:if>
                    </tbody>
                </table>
                <a href="MainController?btnAction=logout">LogOut</a>
                <div class="pagination">
                    <c:forEach  begin="1" end="${requestScope.countPageSize}" var="i">
                        <a id="${i}" href="DispatchController?btnAction=Search Request&index=${i}&key=${requestScope.key}&StatusRequest=${requestScope.StatusRequest}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
        </form>



    </body>
</html>