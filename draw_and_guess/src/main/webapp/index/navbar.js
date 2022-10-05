
function getCookie(name){

    let val  = document.cookie.match('(^\;)?'+ name + '=([^;]*)(;|$)');
    return val? val[2] : null;

}


let name1 = getCookie("name");
let id = getCookie("id");
let xml;
let x;

$(document).ready(
function(){

if(id){

    $.ajax("http://localhost:8080/index/navbar.html")
    .done(function(e){
        xml = $(e).find('.loged');
        $("m2").html($(xml));
        $(".id").text(id);
        $(".name").text(name1)
      	  
    });



}else{

    $.ajax("http://localhost:8080/index/navbar.html")
    .done(function(e){
       
         let refind = $("#m2").html(e).find('.logout');
       	
        setTimeout(()=>$(".id").html(refind),1000);
    });
    
}

;
}
)