import {useCallback, useState} from "react";

const useHttp = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const [data, setData] = useState(null);
    const [time, setTime] = useState(null);


    const sendRequest = useCallback(async (requestConfig) => {
        console.log("sendRequest 시작!")
        let start = new Date();  // 시작
        setIsLoading(true);
        setError(null);
        
        try {
            const response = await fetch(requestConfig.url, {
                method: requestConfig.method ? requestConfig.method : "GET",
                headers: requestConfig.headers ? requestConfig.headers : {},
                body: requestConfig.body ? JSON.stringify(requestConfig.body) : null,
            });
            console.log(response)

            if (!response.ok) {
                console.log("데이터 못받아옴!")
                throw new Error("Request failed!");
            }
            const data = await response.json(); // 값 받아옴
            console.log(JSON.stringify(data))
            setData(JSON.stringify(data));
        } catch (err) {
            console.log("예외발생!" , err)
            setError(err.message || "Something went wrong!");
        }
        let end = new Date();  // 종료
        setTime(start-end);
        setIsLoading(false);
    }, []);

    return {
        isLoading: isLoading,
        error: error,
        data: data,
        sendRequest: sendRequest,
        time: time
    };
};

export default useHttp;