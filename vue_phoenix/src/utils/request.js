import axios from 'axios'
import ElementUI from "element-ui";
/*import router from "@/router";
import {serverIp} from "../../public/config";*/
//封装request.js

//创建request对象
const request = axios.create({
    //请求路径
    baseURL: 'http://127.0.0.1:9090/phoenix',
    timeout: 30000,
    changeOrigin:true,
})

// request 拦截器 请求
// 可以自请求发送后台前 对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    //本地存储去拿登录信息
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    if (user) {
        if(user.student){
            user = user.student
            config.headers['token'] = user.token;  // 设置请求头
        }else{
            user = user.teacher
            config.headers['token'] = user.token;  // 设置请求头
        }
    }

    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器 结果
// 可以在接口响应后 接收后台数据统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 当权限验证不通过的时候给出提示
        if (res.code === '401') {
            ElementUI.Message({
                message: res.obj,
                type: 'error'
            });
            router.push("/login")
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

