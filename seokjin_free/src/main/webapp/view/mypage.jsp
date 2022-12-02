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
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mypage.css">
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
                    <div style="width: 100%;">               
                        <h5 class="text-light mb-5" style="text-align: center;">로그인에 성공하였습니다.</h5>
                     
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
                                      <tr>
                                        <th>1</th>
                                        <td>${user.getUsername()}</td>
                                        <td>${user.getMessage()}</td>
                                        <td>${user.getWin()}승</td>
                                        <td>${user.getLoss()}패</td>
                                        <td>${user.getWin_loss_ratio()}%</td>
                                      </tr>
                                 
                                     
                                    </tbody>
                                  </table>
                                 
                            </div>
                         
                          <button type="submit" class="btn btn-danger mb-3 bg-dark bg-gradient text-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="width: 100%;">
	                                정보 수정
	               		 </button>
                        <button onclick="location.href='http://localhost:8080/seokjin_free/UserServlet?cmd=signout'" type="button" class="btn btn-danger bg-dark bg-gradient text-danger" style="width: 100%;">
                            로그아웃
                  	   </button>
                       
                    </div>
                        
                </div>
            </div>
            
   
       </div>
            
        
    </div>
    
    
    <!-- 정보 수정 Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content bg-dark bg-gradient">
	      <div class="modal-header bg-dark bg-gradient text-light">
	        <h1 class="modal-title fs-5" id="staticBackdropLabel">정보 수정</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="http://localhost:8080/seokjin_free/UserServlet?cmd=updatemypage" method="post">
		      <div class="modal-body">
		        
	              <label for="title" class="form-label text-light">상태 메시지</label>
	              <input type="text" class="form-control" id="message" name="message" value="${user.getMessage()}">
	              <input type="hidden" class="form-control" id="username" name="username" value="${user.getUsername()}">
	                         
		      </div>
		      <div class="modal-footer">
		      
		         <button type="submit" class="btn btn-danger bg-dark bg-gradient text-danger" style="width: 100%;">
		                                설정 완료
		          </button>
		      </div>
		 </form>
	    </div>
	  </div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
</body>

</html>