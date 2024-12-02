import axios from 'axios'


const baseURL = '/api'
const instance = axios.create({baseURL})
import {ElMessage} from 'element-plus'

import {user_token} from '@/stores/token.ts'
import router from "@/router";

instance.interceptors.request.use(
    (config) => {
        const user_token_data:any = user_token();
        if (user_token_data.token) {
            config.headers.Authorization = user_token_data.token.token
        }
        return config
    },
    (err) => {
        console.log(err)
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    (result) => {
        return result;
    },
    async (err) => {
        if (err.response.status === 401) {
            ElMessage.error({ message: '登录过期', grouping: true });
            // 使用router.push返回的Promise来处理跳转
            await router.push({name: 'about'})
            window.location.reload();
            return Promise.reject(err);
        } else {
            ElMessage.error({ message: '服务异常', grouping: true });
        }
        return Promise.reject(err); // 异步的状态转化成失败的状态
    }
);

export default instance
