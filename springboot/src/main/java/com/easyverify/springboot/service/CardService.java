package com.easyverify.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easyverify.springboot.entity.EasyCard;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.vo.CardVo;
import com.easyverify.springboot.vo.PageBean;

public interface CardService extends IService<EasyCard> {
    /**
     * 分页查询 卡片列表
     * @param currentPage 当前页码
     * @param pageSize 每页显示条数
     * @return PageBean
     */
    PageBean<CardVo> get_card_list(Integer currentPage, Integer pageSize);

    /**
     * 根据项目id获取项目信息
     * @param id 项目id
     * @return EasyProject
     */
    EasyProject get_project_by_id(Integer id);

}
