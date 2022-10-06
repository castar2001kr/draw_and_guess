
let re;

function refajax(){
	
    $.ajax("/game/roomlist")
    .done((data)=>{
    
    $(".roomlist").text("");
    
    re=data;
    console.log(data);
        JSON.parse(data).result.forEach((e) => {
            
            let box=document.createElement("div");
            box.className="room";

            let title = document.createElement("span");
            title.innerText = e.name;
            title.className = "tit"
            console.log(e.name)
          

            let rid = document.createElement("span");
            rid.className = "iid"
            rid.innerText = e.id;
            console.log(e.id);

            $(box).append(title).append(rid);

            $(".roomlist").append(box);
        });

        $(".room").append("<button>ENTER</button>");
        $(".room").find("button").click(()=>{


            let num = ($(this).parent().find(".iid").text()-0);
            let tit = ($(this).parent().find(".tit").text());
            
            $.ajax("/game/"+num+"?title="+tit).done((e)=>{

                if(e.result){
                    alert("true!!!")
                }else{
                    alert("false!!!")
                }

            })

        }

            
        )


    })

}


function makajax(){

    location.href="/game/newroom";

}


$.ajax("/container/roomcontainer.html")
.done(
    (e)=>{
        let refind = document.createElement("div");
         refind = $(refind).html(e).find(".containerofview");
		$(".con").html(refind);


        $(".refresh").click(()=>{refajax();});
        $(".make").click(()=>{makajax()});










    }

)