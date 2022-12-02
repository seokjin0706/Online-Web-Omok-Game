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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/sign_up.css">
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
            <div class="bg-dark shadow-lg rounded inner-wrapper" style="width: 450px; height: 450px;">
             
                <div>
                    <div>                
                        <h1 class="text-light mb-5">Create an account</h1>
                        <form action="http://localhost:8080/seokjin_free/UserServlet?cmd=join" method="post">
                            <div class="mb-3">
                              <label for="name" class="form-label text-light">Username</label>
                              <input type="text" class="form-control" id="username" name="username" autofocus required placeholder="공백없이 입력하세요">
                            </div>
                            <div class="mb-3">
                              <label for="passwd" class="form-label text-light" >Password</label>
                              <input type="password" class="form-control" id="password" name="password" required placeholder="공백없이 입력하세요">
                            </div>
                            <div class="mb-3">
                                <label for="eamil" class="form-label text-light">Email address</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
                              </div>

                            <button type="submit" class="btn btn-danger mb-3 bg-dark bg-gradient text-danger" style="width: 100%;">
                                Create an account
                            </button>
                          </form>
                    </div>
                        
                </div>
            </div>
            
   
       </div>
            
        
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
</body>

</html>