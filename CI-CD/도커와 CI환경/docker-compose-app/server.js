const express = require("express");
const redis = require("redis");

// 레디스 클라이언트 생성
const client = redis.createClient({
    // 도커 Compose를 사용할 때는 docker-compose.yml 파일에 명시한 컨테이너 이름으로 주면 된다.
    host: "redis-server",
    // redis 기본 포트 번호(6379)
    port: 6379
})

const app = express();

// 숫자는 0부터 시작
client.set("number", 0);

app.get('/', (req, res) => {
    client.get("number", (err, number)=>{
        // 현재 숫자를 가져온 후에 1씩 올려준다.
        client.set("number", parseInt(number)+1)
        res.send('숫자가 1씩 올라갑니다. 숫자: ' + number)
    })
})


app.listen(9000);
console.log('Server is running');