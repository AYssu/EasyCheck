package com.easyverify.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksVo {
    private Integer aid;

    private String link;

    private Integer projectId;

    private Integer type;

    private Integer code;

    private Integer codeType;

    private Integer safeType;

    private Integer returnTime;

}
