package com.easyverify.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyverify.springboot.entity.EasyCard;
import com.easyverify.springboot.entity.EasyProject;
import com.easyverify.springboot.entity.EasyUser;
import com.easyverify.springboot.mapper.CardMapper;
import com.easyverify.springboot.mapper.ProjectMapper;
import com.easyverify.springboot.service.CardService;
import com.easyverify.springboot.service.ProjectService;
import com.easyverify.springboot.service.UserService;
import com.easyverify.springboot.vo.CardVo;
import com.easyverify.springboot.vo.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CardServiceImpl extends ServiceImpl<CardMapper, EasyCard> implements CardService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public PageBean<CardVo> get_card_list(Integer currentPage, Integer pageSize) {
        // 获取jwt 中的用户ID 并查询到实体类
        EasyUser user = userService.get_user_by_jwt();

        Page<EasyCard> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<EasyCard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EasyCard::getUid, user.getUserId());

        // 执行查询 根据当前用户ID查询Card
        Page<EasyCard> easyCardPage = cardMapper.selectPage(page, queryWrapper);
        List<EasyCard> records = easyCardPage.getRecords();
        List<CardVo> cardVOS = records.stream().map(record -> {
            CardVo cardVo = new CardVo();
            EasyProject project = get_project_by_id(record.getPid());
            BeanUtils.copyProperties(record, cardVo);
            cardVo.setProjectName(project.getProjectName());
            if (record.getCardType()==7)
            {
                // 异常卡
                if (record.getEndTime()==null)
                    record.setEndTime(LocalDateTime.now());
                // 自定义卡默认激活
                LocalDateTime localDateTime = record.getEndTime();
                cardVo.setEndTime(localDateTime);
                boolean isActive = localDateTime.isBefore(LocalDateTime.now());
                cardVo.setCardStatus(isActive?2:3);
            }else {
                if (record.getFirstBindTime()==null)
                {
                    cardVo.setCardStatus(1);
                }else {
                    LocalDateTime localDateTime = getTime(record);
                    cardVo.setEndTime(localDateTime);
                    boolean isActive = localDateTime.isBefore(LocalDateTime.now());
                    cardVo.setCardStatus(isActive?2:3);
                }
            }
            return cardVo;
        }).toList();

        // 设置返回值
        PageBean<CardVo> pageBean = new PageBean<>();
        pageBean.setTotal((int) easyCardPage.getTotal());
        pageBean.setItems(cardVOS);
        return pageBean;
    }

    private static LocalDateTime getTime(EasyCard record) {
        LocalDateTime localDateTime = record.getFirstBindTime();
        Integer cardTime = record.getCardTime();
        if (record.getCardType()==1)
            localDateTime = localDateTime.plusDays(cardTime);
        else if (record.getCardType()==2)
            localDateTime = localDateTime.plusWeeks(cardTime);
        else if (record.getCardType()==3)
            localDateTime = localDateTime.plusMonths(cardTime);
        else if (record.getCardType()==4)
            localDateTime = localDateTime.plusMonths(6L * cardTime);
        else if (record.getCardType()==5)
            localDateTime = localDateTime.plusYears(cardTime);
        else if (record.getCardType()==6)
            localDateTime = localDateTime.plusYears(10);
        return localDateTime;
    }

    @Override
    public EasyProject get_project_by_id(Integer id) {
        EasyProject project = (EasyProject) redisTemplate.opsForValue().get("open_project_" + id);
        if (project != null) {
            return project;
        }
        project = projectMapper.selectById(id);
        redisTemplate.opsForValue().set("open_project_" + id, project, 30, TimeUnit.MINUTES);
        return project;
    }

}
