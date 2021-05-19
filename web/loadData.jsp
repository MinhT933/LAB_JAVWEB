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
        <c:if test='${sessionScope.acc.roleid==2}'>
            <form action="MainController?btnAction=search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search">tìm kiếm</i>
                        </button>
                    </div>
                </div>
            </form>
            <table border="1px solid black" action="btnAction">
                <tr>
                    <th>productID</th>
                    <th>productName</th>
                    <th>color</th>
                    <th>quanlity</th>
                    <th>Category Name</th>
                </tr>
                <c:forEach items="${src}" var="x" varStatus="1">
                    <tr>
                        <td name="id">${x.productID}</td>
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
                    </tr>
                </c:forEach>
                    <div class="paging">
                        <c:forEach begin="1" end="${endpage}" var="i">
                            <a href="#">${i}</a>
                        </c:forEach>
                        
                    </div>
            </table>
            <a href="Update.jsp">accept</a>
            <a href="MainController?btnAction=logout">LogOut</a>
        </c:if>

        <c:if test = "${sessionScope.acc.roleid==1}">
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