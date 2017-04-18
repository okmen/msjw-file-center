package cn.file.service.impl;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.file.cached.impl.IFileCachedImpl;
import cn.file.common.QiniuUpload;
import cn.file.common.QiniuUtil;
import cn.file.dao.IFileDao;
import cn.file.service.IFileService;

@Service("fileService")
public class IFileServiceImpl implements IFileService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IFileCachedImpl fileCache;
	
	
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
	public String uploadFile(String localFile) throws Exception {
		File file = new File(localFile);
        return uploadFile(file);
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
	public String uploadFile(File file) throws Exception {
		//生成图片名称
		String fileName = QiniuUpload.getBigFileName();
		//上传图片
		new QiniuUpload().upload(file.toString(), fileName);
		//返回图片路径
		return getQiniuImgDomain()+fileName;
			
	}
	
	public String getQiniuImgDomain() {
		return QiniuUtil.qiniuImgDomain;
	}

	public String getUpToken() {
		return new QiniuUpload().getUpToken();
	}

    public static void main(String[] args) {
        // 上传文件的路径，因为在Mac下，所以路径和windows下不同
        String filePath = "/Users/Jeff.yang/Downloads/图片/百香翡翠茶.jpg";
        // 要上传的空间
        String bucketName = "admin-resources-bucket";
        // 上传到七牛后保存的文件名
        String key = "getImgByUrl.html";

        try {
			new IFileServiceImpl().uploadFile(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
