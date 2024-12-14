package com.easyverify.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyverify.springboot.entity.EasyCard;
import com.easyverify.springboot.mapper.CardMapper;
import com.easyverify.springboot.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, EasyCard> implements CardService {
}
