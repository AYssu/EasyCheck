import request from '@/utils/request.ts'

/*
 * 用户注册
 * @params params
 * username  用户名
 * email 用户邮箱
 * password 用户密码
 * agree 同意协议
 * @return Promise
 */
export const user_register_services = (params: any) => {
    return request.post('/user/register', params)
}