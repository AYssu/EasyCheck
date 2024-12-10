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

/*
 * 用户登录
 * @params params
 * username  用户名
 * password 用户密码
 * agree 同意协议
 * @return Promise
 */
export const user_login_services = (params: any) => {
  return request.post('/user/login', params)
}

/*
 * 获取邮箱验证码
 * @params params
 * email 用户邮箱
 * @return Promise
 */
export const user_register_code_services = (email: any) => {
  return request.get('/open/send_register_code?email=' + email)
}
