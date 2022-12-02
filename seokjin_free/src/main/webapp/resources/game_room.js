const WHITE = 1;
const BLACK = 2;



	


class Game{
    constructor(num){
        this.startX = 30; // 시작 X 위치
        this.startY = 30; // 시작 Y 위치
        this.n = 15; // 15x15
        this.distance = 30; // 칸 간격
        this.height = this.distance *(this.n - 1); //15x15
        this.width = this.distance *(this.n - 1); //15x15 오목판
        this.boardColor = "(244, 176, 70)"; //오목판 배경색
        this.canvas = document.getElementById("game-board");
        this.board = [
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                     ];
        this.curColor = BLACK;
        /*웹 소켓 연결 */
        // WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
		this.webSocket = new WebSocket(`ws://localhost:8080/seokjin_free/GameRoom/${num}`);
		// WebSocket 서버와 접속이 되면 호출되는 함수
		this.webSocket.onopen = function(message) {
			console.log('GameRoom', num);
		};
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		this.webSocket.onclose = function(message) {
		
		};
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		this.webSocket.onerror = function(message) {
		 
		};
		
		// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		this.webSocket.onmessage = (message) => {
			if(message.data === 'white-win'){
				document.getElementById("win-stone").innerHTML = "흰돌 승리";
				document.getElementById("win-btn").click();
			}else if(message.data === 'black-win'){
				
				document.getElementById("win-stone").innerHTML = "검은돌 승리";
				document.getElementById("win-btn").click();
			}else{
				
				
				message = message.data.split(',');
			
				this.curColor = parseInt(message[0]);
				message = message.slice(1);
				for(let i=0; i<message.length; i++){
					let x = Math.floor(i / this.n);
					let y = i % this.n;
					console.log(x, y);
					this.board[x][y] = parseInt(message[i]);
				}
			    let turn = document.getElementById("turn");
			    console.log(this.curColor, WHITE, BLACK);
		        if(this.curColor === WHITE){
		         	turn.innerText = "흰돌 차례";
					
		        }else if(this.curColor === BLACK){
		         	turn.innerText = "검은돌 차례";
		        };
				this.drawStone();
			}
		};
    }
    
    // Send 버튼을 누르면 호출되는 함수
	sendMessage(message) {
	  this.webSocket.send(message);
	
	}
	// Disconnect 버튼을 누르면 호출되는 함수
	disconnect() {
	  // WebSocket 접속 해제
	  this.webSocket.close();
	}
    
    canvasInit(){
        this.canvas.width = (this.n+1)*this.distance;
        this.canvas.height = (this.n+1)*this.distance;
        this.canvas.addEventListener("click", (e)=>{
            if(this.canvas.getContext){
                var ctx = this.canvas.getContext('2d');
               
                let targetBoardX =Math.round((e.clientX - ctx.canvas.offsetLeft)/this.distance) - 1; //board 배열의 x,y 좌표 매핑
                let targetBoardY =Math.round((e.clientY - ctx.canvas.offsetTop)/this.distance) - 1;
           
                if(this.board[targetBoardX][targetBoardY] !== 0) return; //이미 돌을 놓은 곳이면 무시 
                this.board[targetBoardX][targetBoardY] = this.curColor;
                this.drawStone();
                
                if(this.checkWin(targetBoardX, targetBoardY)){
                    setTimeout(() => {    
                        if(this.curColor !== WHITE){
							document.getElementById("win-stone").innerHTML = "흰돌 승리";
							document.getElementById("win-btn").click();
							this.sendMessage('white-win');
						
  
                        }else{
							document.getElementById("win-stone").innerHTML = "검은돌 승리";
							document.getElementById("win-btn").click();
                           this.sendMessage('black-win');
                        
                        }
                    }, 100);
                        
                }
                let turn = document.getElementById("turn");
                if(this.curColor === WHITE){
                 	this.curColor = BLACK;
                 	turn.innerText = "검은돌 차례";
					
                }else if(this.curColor === BLACK){
					this.curColor = WHITE
                 	turn.innerText = "흰돌 차례";
                };
                //오목판 정보를 string 형태로 방에 있는 모든 클라이언트에게 전달
                this.sendMessage(this.curColor.toString() +',' + this.board.toString()); 
                
            }
        })
    }
    drawBackground(){
        if(this.canvas.getContext){
            var ctx = this.canvas.getContext('2d');
            ctx.fillStyle = `rgb${this.boardColor}`
            ctx.fillRect(this.startX - 30, this.startY - 30, 30*16, 30*16);
        }
    }
    
     //draw horizontal line
    drawHorizontalLine(){
        if(this.canvas.getContext){
            var ctx = this.canvas.getContext('2d');
            for(let i=0; i<this.n; i++){
               ctx.beginPath();
               if(i == 0 || i == this.n-1) ctx.lineWidth = "1.5"; 
               else ctx.lineWidth = "0.8";
               ctx.moveTo(this.startX, this.startY + this.distance *i);
               ctx.lineTo(this.startX + this.width, this.startY + this.distance * i);
      
               ctx.stroke();  
                
            } 
        }
    }
    //draw vertical line
    drawVerticalLine(){
        if(this.canvas.getContext){
            var ctx = this.canvas.getContext('2d');
            for(let i=0; i<this.n; i++){
                ctx.beginPath();
                ctx.moveTo(this.startX + this.distance*i, this.startY);
                ctx.lineTo(this.startX + this.distance*i, this.startY + this.height);
                if(i == 0 || i == this.n-1) ctx.lineWidth = "1.5"; 
                else ctx.lineWidth = "0.8";
                
                
                ctx.stroke();  
                
            } 
        }
    }
    
    drawDot(){
        if(this.canvas.getContext){
            var ctx = this.canvas.getContext('2d');
            let pos = [3, 11, 7];
            for(let i= 0; i<2; i++){
                for(let j=0; j<2; j++){
                    ctx.beginPath();
                    ctx.arc(this.startX + this.distance*pos[i], this.startY + this.distance*pos[j], 3, 0, 2*Math.PI);
                    ctx.fillStyle = 'black';
                    ctx.fill();
                    ctx.stroke();
                }
            }
            ctx.beginPath();
            ctx.arc(this.startX + this.distance*pos[2], this.startY + this.distance*pos[2], 3, 0, 2*Math.PI);
            ctx.fillStyle = 'black';
            ctx.fill();
            ctx.stroke();
             
        }
    }
    drawBoard() {
        this.canvasInit();
        this.drawBackground();
        this.drawHorizontalLine();
        this.drawVerticalLine();
        this.drawDot();
    }
    drawStone(){ 
        if(this.canvas.getContext){
        var ctx = this.canvas.getContext('2d');
        for(let i=0; i<this.n; i++){
            for(let j=0; j<this.n; j++){
                if(this.board[i][j] == 0) continue;
                ctx.beginPath();
                ctx.arc(this.startX + this.distance*i, this.startY + this.distance*j, this.distance/2.2, 0, 2*Math.PI);
                ctx.fillStyle =  this.board[i][j] === WHITE ? 'white': 'black';
                ctx.fill();
                ctx.stroke();
            }
        }
      }
       
    }
    // Check out Of Bound
    OOB(x, y){
        return x >= 0 && y >= 0 && x < this.n && y < this.n;
    }

    //가로 판별        
    checkHorizontal(x, y){
        let cur = this.board[x][y];
        let sx = x;
        let sy = y;
        let cnt = 1;
        while(this.OOB(sx -1, sy) && cur == this.board[sx - 1][sy]){
            cnt++;
            sx--;
        }
        sx = x;
        while(this.OOB(sx + 1, sy) && cur == this.board[sx + 1][sy]){
            cnt++;
            sx++;
        }
        if(cnt >=5) return true;
        return false;
    } 
    //세로 판별
    checkVertical(x, y){
        let cur = this.board[x][y];
        let sx = x;
        let sy = y;
        let cnt = 1;
        while(this.OOB(sx, sy - 1)&& cur == this.board[sx][sy - 1]){
            cnt++;
            sy--;
        }
        sy = y;
        while(this.OOB(sx, sy + 1) && cur == this.board[sx][sy + 1]){
            cnt++;
            sy++;
        }
        if(cnt >=5) return true;
        return false;
    }
    //대각선 판별
    checkDiagonal(x, y){
        // '\' 대각선 판별
        let cur = this.board[x][y];
        let sx = x;
        let sy = y;
        let cnt = 1;
        while(this.OOB(sx - 1, sy - 1) && cur == this.board[sx -1][sy - 1]){
            cnt++;
            sx--;
            sy--;
        }
        sx = x;
        sy = y;
        while(this.OOB(sx + 1, sy + 1) && cur == this.board[sx + 1][sy + 1]){
            cnt++;
            sx++;
            sy++;
        }
        if(cnt>=5) return true;
        // '/' 대각선 판별
        cnt = 1;
        sx = x;
        sy = y;
        while(this.OOB(sx + 1, sy - 1) && cur == this.board[sx + 1][sy - 1]){
            cnt++;
            sx++;
            sy--;
        }
        sx = x;
        sy = y;
        while(this.OOB(sx - 1, sy + 1) && cur == this.board[sx - 1][sy + 1]){
            cnt++;
            sx--;
            sy++;
        }
        if(cnt >=5) return true;
        return false;
    }

    //x, y 위치에 오목알을 놓았을 때 승리 판별
    checkWin(x, y){
        //가로 판별
        if(this.checkHorizontal(x,y)) return true;
        //세로 판별
        if(this.checkVertical(x,y)) return true;
        //대각선 판별
        if(this.checkDiagonal(x,y)) return true;
    }
    

};



	  




function run(num){
    const game = new Game(num);
    game.drawBoard();
    game.drawStone();
}
