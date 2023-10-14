# InfinityDreamers

<img src="/home/nhnacademy/Desktop/Project/mini-project/Project/InfinityDreamers/20231013_151957.jpg">

client에서 server로 request를 보내면 Message에 담아 PARSE.Wire를 통해 URLParser에 보냄

URLParser에서 PARSE.Wire에 담긴 Message Request를 parse한 후 다음 노드로 보냄

Temperature Node또는 Humidity Node로 보낸 후 해당 노드에서 Socket으로 연결하여 EMS에 Requset를 보내고 응답을 받아 Message Reponse에 담고 다음 노드로 이동

ContentsParser에서 format에 맞게 해당 Wire를 통해 다음 Node로 이동

헤당 노드에서 형식에 맞게 변환 후 PARSE.Wire통해 Server로 보내 Client에게 Response를 보냄 