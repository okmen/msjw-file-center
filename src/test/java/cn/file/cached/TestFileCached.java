package cn.file.cached;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.file.bean.UserRegInfo;
import cn.file.bean.WechatUserInfoBean;
import cn.file.cached.IFileCached;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:junit-test.xml"})
public class TestFileCached {
	
	
	@Autowired
	private IFileCached fileCahce;
	
	@BeforeClass
	public static void setup(){
		//测试开始，可以做一些准备工作，比如清除可能存在的冲突key等
	 	System.out.println("prepare for test start");
	}	
	@AfterClass
	public static void teardown(){
		//测试结束,将本次测试的数据进行清理，确保单元测试可以二次运行
		System.out.println("finish test,clear data");
	}
	
	
	@Test
	public void testWechatUserInfo() {
		int id = 911111111;
		WechatUserInfoBean wechatUserInfoBean = new WechatUserInfoBean();
		wechatUserInfoBean.setId(id);
		wechatUserInfoBean.setNickname("test1");
		wechatUserInfoBean.setHeadImgUrl("xxxxx.html");
		wechatUserInfoBean.setOpenId("wr3e32r32rere");
		wechatUserInfoBean.setUpdateTime(new Date());
		boolean set = fileCahce.setWechatUserInfoBean(id, wechatUserInfoBean);
		System.out.println(set);
		WechatUserInfoBean newBean = fileCahce.getWechatUserInfoBean(id);
		System.out.println(newBean.getNickname());
		
	}
	

	@Test
	public void testAddTableToSetAndIsTableExisted() {
		
		UserRegInfo user = new UserRegInfo();
		user.setUserId(888);
		user.setArea("ssss");
		boolean set = fileCahce.setUser(888, user);
		UserRegInfo newUser = fileCahce.getUser(888);
		String userId = "100001";
		
	}
	
}
