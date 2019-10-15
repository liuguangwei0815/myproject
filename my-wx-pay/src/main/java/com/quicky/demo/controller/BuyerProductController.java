package com.quicky.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quicky.demo.bean.ProductCategory;
import com.quicky.demo.bean.ProductInfo;
import com.quicky.demo.server.CategoryService;
import com.quicky.demo.server.ProductService;
import com.quicky.demo.util.ResultVOUtil;
import com.quicky.demo.vo.ProductInfoVO;
import com.quicky.demo.vo.ProductVO;
import com.quicky.demo.vo.ResultVO;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/list")
	public ResultVO list() {
		List<ProductInfo> productInfolist = productService.findUpAll();
		List<Integer> categoryTypeList = productInfolist.stream().map(e -> e.getCategoryType())
				.collect(Collectors.toList());
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
		
		List<ProductVO> volist = new ArrayList<ProductVO>();
		
		for (ProductCategory productCategory : productCategoryList) {
			ProductVO vo = new ProductVO();
			vo.setCategoryName(productCategory.getCategoryName());
			vo.setCategoryType(productCategory.getCategoryType());
			List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
			for (ProductInfo productInfo : productInfolist) {
				if(productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
					ProductInfoVO pio = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, pio);
					productInfoVOList.add(pio);
				}
			}
			vo.setProductInfoVOList(productInfoVOList);
			volist.add(vo);
		}
		
		return ResultVOUtil.success(volist);
	}

}
