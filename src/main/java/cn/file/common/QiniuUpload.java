package cn.file.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.support.logging.Log;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
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
     * @param days 过期天数，该文件days天后删除
	 * @throws QiniuException 
     */
    public void upload(String filePath, String key,Integer days) throws QiniuException {
        try {
        	logger.info("七牛图片上传...图片名称:"+key);
            //调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken());
            
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            
            //如果days大于0  则设置文件存活时间
            if (null != days && 0 < days) {
            	deleteAfterDays(days, key);
			}
            
            logger.info("七牛图片上传结果："+res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error("七牛图片上传错误,错误信息:"+r.toString());
            throw e;
        }
    }
    
    //获取凭证
    public static String getUpToken() {
       Auth auth = Auth.create(PropertyUtil.getProperty("accessKey"), PropertyUtil.getProperty("secretKey"));
       return auth.uploadToken(PropertyUtil.getProperty("bucketName"));
    }
    
    //获取域名
    public static String getQiniuImgDomain() {
        return PropertyUtil.getProperty("qiniuImgDomain");
     }
    
    //设置文件存活时间
    public void deleteAfterDays(int days,String key){
    	logger.info("设置图片"+key+"存活时间:"+days+"天");
    	Auth auth = Auth.create(PropertyUtil.getProperty("accessKey"), PropertyUtil.getProperty("secretKey"));
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
			bucketManager.deleteAfterDays(PropertyUtil.getProperty("bucketName"), key, days);
		} catch (QiniuException e) {
			logger.info("设置文件存活时间失败,文件key:"+key);
			e.printStackTrace();
		}
    }
    
    
	/**
	 * @Title: getBigFileName 
	 * @Description: TODO(生成图片名称) 
	 * @param @param userId
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String getBigFileName(){
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
