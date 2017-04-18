package cn.file.common;

import java.util.Date;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @ClassName: QiniuUpload 
 * @Description: TODO(七牛图片上传) 
 * @author yangzan 
 * @date 2017年4月17日 下午3:30:09 
 *
 */
public class QiniuUpload {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//构造一个带指定Zone对象的配置类
	Configuration cfg = new Configuration(Zone.zone0());
	//创建上传对象
	UploadManager uploadManager = new UploadManager(cfg);
	
	/**
     * 上传
     * @param filePath 文件路径  （也可以是字节数组、或者File对象）
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public  void upload(String filePath, String key) {
        try {
        	logger.info("七牛图片上传...图片名称:"+key);
            //调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken());
            
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            
            logger.info("七牛图片上传结果："+res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
    
    //获取凭证
    public String getUpToken() {
       Auth auth = Auth.create(QiniuUtil.ACCESS_KEY, QiniuUtil.SECRET_KEY);
       return auth.uploadToken(QiniuUtil.bucketName);
    }
    
    public static void main(String[] args) {
		System.out.println(new QiniuUpload().getUpToken());
	}
    
	/**
	 * @Title: getBigFileName 
	 * @Description: TODO(生成图片名称) 
	 * @param @param userId
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public  static String getBigFileName(){
		Date date = new Date();
		String dirName = DateUtil.getNow_YMD();    //日期(new Date())--当前年月日(20130202)--用户ID(12312)--格式(.jpg)
		String bigFileName = createFileName( date, dirName, "jpg", "" );
		return bigFileName;
	}
	private static String createFileName( Date date, String dirName ,  String fileType,  String mark ){
		String fileName = dirName + DateUtil.getHourMinute() + date.getTime()
				 + mark + (int)(1+Math.random()*100000) + "." + fileType;
		
		return fileName;
	}

}
