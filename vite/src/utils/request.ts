//导入axios  npm install axios
import axios from 'axios'
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api'
const instance = axios.create({baseURL})
import {ElMessage} from 'element-plus'
//添加请求拦截器
import {useTokenStores} from '@/stores/token.ts'
const tokenStore = useTokenStores()
instance.interceptors.request.use(
    (config) => {
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token
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


        if (result.data.code==null||result.data.code.length<=1)
            return result.data

        if (result.data.code === 200) {
            return result;
        }

        ElMessage.error(result.data.message ? result.data.message : '服务异常');
        return result; // 根据业务逻辑决定是否需要返回result或Promise.reject(result)

    },
    async (err) => {
        if (err.response.status === 401) {
            ElMessage.error({ message: '登录过期', grouping: true });

            // 使用router.push返回的Promise来处理跳转
            window.location.reload();
            return Promise.reject(err);
        } else {
            ElMessage.error({ message: '服务异常', grouping: true });
        }
        return Promise.reject(err); // 异步的状态转化成失败的状态
    }
);


export default instance
