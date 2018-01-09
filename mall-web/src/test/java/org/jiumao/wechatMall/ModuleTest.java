package org.jiumao.wechatMall;

import static org.junit.Assert.*;

import org.jiumao.common.utils.IdUtil;
import org.junit.Test;

public class ModuleTest {
	
	
	@Test
	public void IdGTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			IdUtil.getCardId();
			IdUtil.getUserId();
		}
	}
	
	@Test
	public void binTest() throws Exception {
		System.out.println(1 << 3);
	}
	
	

}
