# draw_and_guess
draw_and_guess game


1. directory : game - JS
socket 인터페이스를 이용하여 소켓을 생성하고 인터페이스의 함수를 바꾸어가며 이벤트를 교체한다.

socket에 전달되는 메시지들은 JSON 형식으로,
{
  header : {type : //0(클라이언트 - 호스트로 가는 메시지) // 1(wait 모드로 전환하라는 신호) // 2(sw-host()) 
  // 3(sw-client) // 5(클라이언트가 호스트 또는 클라이언트로 모드를 변화시켰다는 메시지)} // 7(호스트 메시지) // 8(정답 메시지)
  
  body : // 메시지의 내용이 담긴다

}



