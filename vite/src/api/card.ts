import request from '@/utils/request';

/**
 * 获取卡密列表
 * @param params
 */
export const get_project_card_list_services = (params: any) => {
	return request.post('/card/get_card_list', params);
};
