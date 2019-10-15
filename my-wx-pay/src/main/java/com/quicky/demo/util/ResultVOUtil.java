package com.quicky.demo.util;

import com.quicky.demo.vo.ResultVO;

/**
 * 返回前端
 * 
 * @author Administrator
 *
 */
public class ResultVOUtil {

	public static ResultVO success(Object obj) {
		ResultVO vo = new ResultVO();
		vo.setCode(0);
		vo.setMsg("成功");
		vo.setData(obj);
		return vo;
	}

	public static ResultVO success() {
		return success(null);
	}

	public static ResultVO error(Integer code, String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		return resultVO;
	}

}
