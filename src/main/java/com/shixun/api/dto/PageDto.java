package com.shixun.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto {
    // 当前页
    private Long pageNum;
    // 每页大小
    private Long size;


}
