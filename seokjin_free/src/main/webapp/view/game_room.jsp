<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>오목 게임</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/game_room.css">
</head>

<body onload="run(${room.getNum()});">

	<div>
		<ul class="nav bg-dark bg-gradient">
			<li class="nav-item flex-grow-1"><a class="nav-link disabled">오목
					게임</a></li>
			<li class="nav-item"><a class="nav-link"
				href="http://localhost:8080/seokjin_free/UserServlet?cmd=ranking">랭킹</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="http://localhost:8080/seokjin_free/RoomServlet?cmd=singleGameRoom">싱글 플레이</a></li>
			<li class="nav-item"><a class="nav-link"
				href="http://localhost:8080/seokjin_free/RoomServlet?cmd=roomlist">대국하기</a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="http://localhost:8080/seokjin_free/UserServlet?cmd=signin"><c:out
						value="${user.getUsername()}" default="로그인"></c:out></a></li>

		</ul>

	</div>
	<div class="bg-dark bg-gradient" style="height: 100vh">
		<div>
			 
			<h3 class="text-danger bg-gradient" style="text-align: center;">방 제목: ${room.getTitle()}</h3>
			<div class="wrapper">
				<div>
					<div class="card bg-dark bg-gradient text-light " style="min-width: 14rem; max-height: 100%; width: 100%">
						<h5 class="card-header bg-dark bg-gradient text-light" id="turn" style="text-align: center;">검은돌 차례</h5>		
					</div>
					<div>
					</div>
					<div>
						<canvas id="game-board"></canvas>
					</div>
					 <button type="submit" class="btn btn-danger mb-3 bg-dark bg-gradient text-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="width: 100%;">
	                                게임방 설정
	                </button>
				    <button onclick="location.href='http://localhost:8080/seokjin_free/RoomServlet?cmd=roomlist'" type="submit" class="btn btn-danger mb-3 bg-dark bg-gradient text-danger" style="width: 100%;">
	                                게임방 나가기
	                </button>
					
	
				</div>
				
				<div class="bg-dark shadow-lg rounded inner-wrapper" style="width: 500px; height: 640px;">
	
					<div class="card bg-dark bg-gradient text-light " style="min-width: 14rem; max-height: 100%; width: 100%">
						<h5 class="card-header bg-dark bg-gradient text-light">${room.getFounder()}</h5>
						<div class="card-body ">
							<p class="card-text">0승 0패</p>
						</div>
						<h5 class="card-header bg-dark bg-gradient text-light">${user.getUsername()}</h5>
						<div class="card-body">
							<p class="card-text">0승 0패</p>
						</div>
					</div>
	
	
				</div>
			</div>
			
			

		</div>

		
	</div>



	
	<!-- 게임방 설정 Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content bg-dark bg-gradient">
	      <div class="modal-header bg-dark bg-gradient text-light">
	        <h1 class="modal-title fs-5" id="staticBackdropLabel">게임방 설정</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="http://localhost:8080/seokjin_free/RoomServlet?cmd=updateroom" method="post">
		      <div class="modal-body">
		        
	              <label for="title" class="form-label text-light">방 제목</label>
	              <input type="text" class="form-control" id="title" name="title">
	              <input type="hidden" class="form-control" id="num" name="num" value="${room.getNum()}">
	                         
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


	<!-- 게임 종료 Modal trigger -->
   <button type="button" id="win-btn" style="display: none" class="btn btn-danger mb-3 bg-dark bg-gradient text-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop2"></button>         
	<!-- 게임 종료 Modal -->
		<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content bg-dark bg-gradient">
		      <div class="modal-header bg-dark bg-gradient text-light">
		        <h1 class="modal-title fs-5" id="win-stone" style="margin:0 auto">흰돌 승리</h1>
		     </div>
		      
		        <div class="modal-footer">
		        <button onclick="location.href='http://localhost:8080/seokjin_free/RoomServlet?cmd=enterroom&num=${room.getNum()}'" type="button" class="btn btn-danger bg-dark bg-gradient text-danger" data-bs-dismiss="modal">확인</button>
		      </div>
		    
		    </div>
		  </div>
		</div>


	<script src="${pageContext.request.contextPath}/resources/game_room.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>

</html>