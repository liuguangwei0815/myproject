package com.quicky.demo.learn.lockAndsyn;
/**
 * 覆写（接口实现）
 * 重写（子类重写父类）
 * 重载（方法名称，返回不变，参数变化）
 * @author Administrator
 *
 */
public class OverTestChrid extends OverTestParent{

	@Override
	public void test() {
		// TODO Auto-generated method stub
		super.test();
	}
	
	public void test(String name) {
		// TODO Auto-generated method stub
		super.test();
	}

	public  String test(String name,String a) {
		// TODO Auto-generated method stub
		return "";
	}
	
	
}
