1. voice room 입장 요청

```json
//request
{ "userId": 1, "channelId": 3 }
```

2. 현재 요청한 채널에 참여중인 인원 리스트

```json
//response
data: {
  "type": "existingParticipants",
  "participants": [
    { "id": 1, "nickname": "suzie", "imgUrl": "httpblah" },
    { "id": 4, "nickname": "soojin", "imgUrl": "httpblah" },
    { "id": 6, "nickname": "leesj", "imgUrl": "httpblah" }
  ]
}
```

3. sdpOffer

```json
//request
{ "type": "sdpOffer", "userId": 4, "sdpOffer": "sdpOfferblahblah" }
```

4. candidate

```json
//request
{
  "type": "onIceCandidate",
  "userId": 2,
  "candidateInfo": {
    "candidate": "candidate:42012849 1 udp 21blahblah",
    "sdpMid": "0",
    "sdpMLineIndex": 0
  }
}
```

5. ICEcandidate

```json
//response
"data": {
  "type": "iceCandidate",
  "userId": 2,
  "candidateInfo": {
    "candidate": "candidate:1 1 UDP 2015363327 172.17.0.2 5043 typ host",
    "sdpMid": "1",
    "sdpMLineIndex": 1
  }
}
```

6. sdpAnswer

```json
//response
"data": {
  "type": "sdpAnswer",
  "userId": 3,
  "sdpAnswer": "sdpAnswerblahblah"
}
```

7. 새로운 유저 입장

```json
//response
"data": { "type": "newParticipantArrived", "userId": 4 }
```
