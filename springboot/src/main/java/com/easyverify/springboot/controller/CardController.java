package com.easyverify.springboot.controller;

import com.easyverify.springboot.dto.CardListDTO;
import com.easyverify.springboot.service.CardService;
import com.easyverify.springboot.vo.CardVo;
import com.easyverify.springboot.vo.PageBean;
import com.easyverify.springboot.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;
    @PostMapping("/get_card_list")
    public ResponseResult<?> get_card_list(@RequestBody @Validated CardListDTO cardListDTO)
    {
        log.info("card_list_form: {}", cardListDTO);
        PageBean<CardVo> pageBean = cardService.get_card_list(cardListDTO.getCurrentPage(), cardListDTO.getPageSize());
        return ResponseResult.success("查询成功",pageBean);
    }
}
