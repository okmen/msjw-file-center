package cn.file.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.file.common.QiniuUpload;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:junit-test.xml" })
public class TestFileService {

    @Autowired
    @Qualifier("fileService")
    private IFileService fileService;
    

	@Test
	public void testInsertWechatInfo() {
//		System.out.println(fileService.getUpToken());
		System.out.println(fileService.getQiniuImgDomain());
//		System.out.println(new QiniuUpload().getQiniuImgDomain());
		
		
	}
	
	
	@Test
	public void uploadFile() {
//		fileService
		try {
			fileService.uploadFile("/Users/Jeff.yang/Downloads/图片/百香翡翠茶.jpg",0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUpToken() {
//		System.out.println(fileService.getUpToken());
		System.out.println(fileService.getUpToken());
		
	}
	
	
    

}
