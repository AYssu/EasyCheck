import request from '@/utils/request.ts'


/**
 * 获取项目列表
 * @params params
 * currentPage 当前页码
 * pageSize 每页显示条数
 * projectName 项目名称
 */
export const get_project_list_services = (params: any) => {
    return request.post('/project/project_list', params)
}

/**
 * 创建项目
 * @params params
 * projectName 项目名称
 * projectMessage 项目描述
 */

export const create_project_services = (params: any) => {
    return request.post('/project/project_create', params)
}

/**
 * 更新程序的状态
 * @id 程序的ID
 * @return Promise
 */
export const update_project_status_services = (id: any) => {
    return request.get('/project/project_set_status?id='+id)
}

/**
 * 更新项目的信息
 * @params params
 * @return Promise
 */
export const update_project_normal_info_services = (params: any) => {
    return request.post('/project/project_update_info', params)
}

/**
 * 重置项目key
 * @params params
 * @return Promise
 */
export const update_project_reset_key_services = (pid:number)=>{
    return request.get('/project/project_reset_key?pid='+pid)
}

/**
 * 获取项目绑定的用户列表
 * @params params
 * @return Promise
 */
export const get_project_user_list_services = (params: any)=>{
    return request.post('/project/project_bind_list', params)
}

/**
 * 获取项目变量
 * @params params
 * @return Promise
 */
export const get_project_variable_services = (pid: number)=>{
    return request.get('/project/project_get_variable?pid=' + pid)
}

/**
 * 设置项目变量
 * @params params
 * @return Promise
 */
export const set_project_variable_services = (params: any)=>{
    return request.post('/project/project_set_variable', params)
}

/**
 * 设置项目默认更新方式
 * @params params
 * @return Promise
 */
export const set_project_default_update_services = (pid: number)=>{
    return request.get('/project/project_set_default_update?pid='+pid);
}

/**
 * 获取项目更新信息
 * @params params
 * @return Promise
 */
export const get_project_update_info_services = (pid: number)=>{
    return request.get('/project/project_get_update?pid='+pid);
}


/**
 * 设置项目更新信息
 * @param params
 * @return Promise
 */
export const set_project_update_info_services = (params: any)=>{
    return request.post('/project/project_update_update', params);
}

/**
 * 设置项目公告
 * @param params
 * @return Promise
 */
export const set_project_notice_services = (params: any)=>{
    return request.post('/project/project_update_notice', params);
}

/**
 * 获取项目链接
 * @param pid 程序ID
 * @return Promise
 */
export const get_project_links_services = (pid: number)=>{
    return request.get('/project/project_get_link?pid='+pid);
}

/**
 * 新增项目链接
 * @param params
 * @return Promise
 */
export const add_project_link_services = (params: any)=>{
    return request.post('/project/project_add_link', params);
}


/**
 * 更新项目链接
 * @param params
 * @return Promise
 */
export const update_project_link_services = (params: any)=>{
    return request.post('/project/project_update_link', params);
}

/**
 * 删除项目链接
 * @param aid 链接ID
 * @return Promise
 */
export const delete_project_link_services = (aid: number)=>{
    return request.get('/project/project_link_delete?aid='+aid);
}