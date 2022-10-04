class drawer_for_client{
 
    constructor(canvas){

        this.ctx=canvas.getContext();//t
        this.bool = false;


        this.working= false;
        this.buffer = [];

        this.funcs = [this.dot, this.line_start, this. line_to, this.line_end];
    }

    listen(e){ // e.data.b = array 형태여야하며, 각 원소는 {x: , y: , delay: } 형태

        let arr = e.data.b;

        while(arr[0]!=null){
            this.buffer.push(arr.shift());
        }

        this.order();
    }


    dot(x,y){
        this.ctx.fillRect(x,y,2,2);
    }


    line_start(x,y){
        
        if(!this.bool){

            this.dot(x,y);
            this.ctx.beginPath();
            this.ctx.moveTo(x,y);
            this.ctx.lineTo(x,y);
        }

    }

    line_to(x,y){
        
        if(this.bool){
            this.ctx.lineTo(x,y);
            this.ctx.stroke();
        }
    }

    line_end(x,y){

        if(this.bool){
            this.bool=!this.bool;
        }
    }

    order(){
        if(!this.working){
            this.do();
        }

    }

    do(){ // 재귀적 호출
        
        let that = this;

        if(this.buffer[0]!=null){

            let e=buffer.shift();
            
            setTimeout(()=>{

                that.funcs[e.type](e.x,e.y);
                that.do();

            },e.delay);



        }

    }

}



class Router{

    constructor(){

        this.canvas = document.querySelector(".canvas");

        this.dfc = new drawer_for_client(this.canvas);
            
        this.dispatcher=[];
        this.dispatcher.shift([(d,mode)=>this.onans(d,mode)]);  //0
        this.dispatcher.shift([]);  //1
        this.dispatcher.shift(null);
        this.dispatcher.shift([(d,mode)=>this.ondraw(d,mode)]);//3
        this.dispatcher.shift(null);
        this.dispatcher.shift([(d,mode)=>this.onchat(d,mode)])//5

        this.dispatcher[1].shift(null);
        this.dispatcher[1].shift((d,mode)=>this.onplay(d,mode));
        this.dispatcher[1].shift((d,mode)=>this.onstop(d,mode));
        this.dispatcher[1].shift((d,mode)=>this.onenter(d,mode));

    }

    onans(d,mode){};      //
    
    onplay(d,mode){};     //

    onstop(d,mode){};     //

    onenter(d,mode){};    //

    onout(d,mode){};      //

    onchat(d,mode){};     //

    ondraw(d,mode){

        if(mode){return;}
        this.dfc.listen(d.data.b); // b = body


    };     //
}

class SocketInterface{

    constructor(){

        this.sock=new WebSocket();
        this.sock.onmessage=this.onmessage;
        this.sock.onopen=this.onopen;
        this.sock.onclose=this.onclose;
        
        this.router=new Router();

        this.mode=false; // client mode : false, host mode : true

    }


    modechange(){

        if(this.mode){
            this.mode=!this.mode;
        }

        this.onmessage=(e)=>{

            let header = e.data.h;
            let action = e.data.a? e.data.a : 0;

            this.router.dispatcher[header][action](e.data, this.mode);

        }


        this.onclose=()=>{


        }

        this.onopen=()=>{


        }
        
    }


    getSock(){
        return this.sock;
    }

}