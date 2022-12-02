<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>오목 게임</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ranking.css">
</head>

<body>
    


    <div>
        <ul class="nav bg-dark bg-gradient">
            <li class="nav-item flex-grow-1">
              <a class="nav-link disabled">오목 게임</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/seokjin_free/UserServlet?cmd=ranking">랭킹</a>
              </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/seokjin_free/RoomServlet?cmd=singleGameRoom">싱글 플레이</a>
              </li>
            <li class="nav-item">
              <a class="nav-link" href="http://localhost:8080/seokjin_free/RoomServlet?cmd=roomlist">대국하기</a>
            </li>
            <li class="nav-item">
                 <a class="nav-link" href="http://localhost:8080/seokjin_free/UserServlet?cmd=signin"><c:out value="${user.getUsername()}" default="로그인"></c:out></a>
         </li>
          
        </ul>
       
    </div>
   
    <div class="bg-dark bg-gradient">
        <div class="wrapper">    
            <div style="width: 100%;">
                <table class="table table-dark table-striped table-bordered ">
                    <thead>
                      <tr>
                        <th scope="col">순위</th>
                        <th scope="col">Username</th>
                        <th scope="col">상태 메시지</th>
                        <th scope="col">승</th>
                        <th scope="col">패</th>
                        <th scope="col">승패 비율</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                    	   <tr>
		                        <th scope="row">1</th>
		                        <td><c:out value="${user.username}"></c:out></td>
		                        <td>${user.message}</td>
		                        <td><c:out value="${user.win}승"></c:out></td>
		                        <td><c:out value="${user.loss}패"></c:out></td>
		                        <td><c:out value="${user.win_loss_ratio}%"></c:out></td>
		                         
                    	  </tr>
                    </c:forEach>
                   
                 
                     
                    </tbody>
                  </table>
                 
            </div>
        
   
       </div>
            
        
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
</body>

</html>