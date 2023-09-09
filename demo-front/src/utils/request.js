import router from "@/router";
import axios from "axios"






const request = axios.create({
    baseURL: 'http://127.0.0.1:9090',
    // baseURL: 'http://172.20.10.3:9090',
    timeout: 30000
})

//request拦截器
//可以自请求发送前对请求做一些处理
//比如统一加token,对请求参数统一加密

request.interceptors.request.use(config =>{
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = JSON.parse(localStorage.getItem("honey-user") || '{}') 
    config.headers['token'] = user.token ;                                   //设置请求头

    return config
}, error =>{
    console.error('request error'+ error) //for debug
    return Promise.reject(error)
});

//response 拦截器
//可以在接口响应后统一处理结果

request.interceptors.response.use(
    response => {
        let res = response.data;

        //兼容服务端返回的字符数据
        if(typeof res === 'string'){
            res = res ? JSON.parse(res) : res
        }
        if(res.code === '401'){
            router.push('/login')
        }
        return res;
    },
    error =>{
        console.error('response error'+ error) //for debug
        return Promise.reject(error)
    }
)


export default request