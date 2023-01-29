import React from "react";
import "./App.css";
import useHttp from "./hooks/use-hook";

function App() {
    const {isLoading: isLoading1,
        error: error1,
        data: data1,
        sendRequest: fetchTasks1,
        time: time1} = useHttp();
    const {isLoading: isLoading2,
        error: error2,
        data: data2,
        sendRequest: fetchTasks2,
        time: time2} = useHttp();

    const baseUrl = "http://localhost:8080/"

    async function getRedis() {
        result1 = null
        const requestConfig = {
            url: baseUrl + "redis/1",
        };
        await fetchTasks1(requestConfig);
    }

    async function getDatabase() {
        result2 = null
        const requestConfig = {
            url: baseUrl + "normal/1",
        };
        await fetchTasks2(requestConfig);
    }

    let result1 = null;
    let result2 = null;

    if (error1) {
        result1 = "error"
    } else if (isLoading1) {
        result1 = "Loading"
    } else if(data1) {
        result1 = `조회속도 : ${time1}`
    }

    if (error2) {
        result2 = "error"
    } else if (isLoading2) {
        result2 = "Loading"
    } else if(data2) {
        result2 = `조회속도 : ${time2}`
    }

    return (

        <React.Fragment>
            <section style={{background : "black"}}>
                <h1 style={{color: "white"}}> Redis로 데이터베이스 1만개 성능측정하기!!</h1>
                <section><button onClick={getRedis}>getRedisData</button> <div>{result1}</div></section>
                <section><button onClick={getDatabase}>getDatabase</button> <div>{result2}</div></section>
            </section>


        </React.Fragment>
    );
}

export default App;
