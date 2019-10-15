package com.quicky.demo.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品(包含类目)
 * Created by 廖师兄
 * 2017-05-12 14:20
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 7097863777546530545L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
