package org.jiumao.wechatMall;

import static org.junit.Assert.*;

import org.jiumao.common.utils.IdGenerator;
import org.junit.Test;

public class ModuleTest {
	
	
	@Test
	public void IdGTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			IdGenerator.getCardId();
			IdGenerator.getUserId();
			IdGenerator.backup();
		}
	}
	
	@Test
	public void binTest() throws Exception {
		System.out.println(1 << 3);
	}
	
	

}
