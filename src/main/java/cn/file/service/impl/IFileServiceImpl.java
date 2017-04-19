package cn.file.service.impl;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.file.common.QiniuUpload;
import cn.file.service.IFileService;

/**
 * @ClassName: IFileServiceImpl 
 * @Description: TODO(文件处理实现) 
 * @author yangzan 
 * @date 2017年4月19日 下午2:31:42 
 *
 */
@Service("fileService")
public class IFileServiceImpl implements IFileService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

	
	/**
	 * @Title: uploadFile 
	 * @Description: TODO(通过文件路径上传文件) 
	 * @param @param localFile
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	@Override
	public String uploadFile(String localFile,Integer days) throws Exception {
		logger.info("【七牛云】上传文件路径:"+localFile);
		File file = new File(localFile);
        return uploadFile(file,days);
	}

	
	/**
	 * @Title: uploadFile 
	 * @Description: TODO(通过File上传文件) 
	 * @param @param file
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	@Override
	public String uploadFile(File file,Integer days) throws Exception {
		logger.info("【七牛云】上传文件:"+file);
		//生成图片名称
		String fileName = QiniuUpload.getBigFileName();
		//上传图片
		new QiniuUpload().upload(file.toString(), fileName,days);
		//返回图片路径
		return getQiniuImgDomain()+fileName;
	}
	
	/**
	 * 获取图片域名
	 */
	public String getQiniuImgDomain() {
		return QiniuUpload.getQiniuImgDomain();
	}

	/**
	 * 获取上传凭证
	 */
	public String getUpToken() {
		return QiniuUpload.getUpToken();
	}
	
	
	
    public static void main(String[] args) {
    	
//	    System.out.println(new IFileServiceImpl().getQiniuImgDomain());
        // 上传文件的路径，因为在Mac下，所以路径和windows下不同
//	    String filePath = "/Users/Jeff.yang/Downloads/图片/百香翡翠茶.jpg";
	    // 要上传的空间
	    //String bucketName = "admin-resources-bucket";
	    // 上传到七牛后保存的文件名
	    //String key = "getImgByUrl.html";
//	
//	     try {
//			new IFileServiceImpl().uploadFile(filePath);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

}