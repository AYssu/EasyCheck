import request from '@/utils/request.ts'


/*
 * 获取项目列表
 * @params params
 * currentPage 当前页码
 * pageSize 每页显示条数
 * projectName 项目名称
 */
export const get_project_list_services = (params: any) => {
    return request.post('/project/project_list', params)
}

/*
 * 创建项目
 * @params params
 * projectName 项目名称
 * projectMessage 项目描述
 */

export const create_project_services = (params: any) => {
    return request.post('/project/project_create', params)
}