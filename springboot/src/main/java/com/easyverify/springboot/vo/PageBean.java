package com.easyverify.springboot.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private int total;//总条数
    private List<T> items;//当前页数据集合
    private List<String> names;// 获取所有程序合集名称
}
