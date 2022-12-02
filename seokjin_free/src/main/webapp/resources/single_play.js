const WHITE = 1;
const BLACK = 2;


	
	


class Game{
    constructor(){
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



	  




function run(){
    const game = new Game();
    game.drawBoard();
    game.drawStone();
}
