package cn.file.common;

/**
 * @ClassName: QiniuUtil 
 * @Description: TODO(七牛图片上传配置信息工具类) 
 * @author yangzan 
 * @date 2017年4月17日 下午3:11:00 
 *
 */
public class QiniuUtil {
	public static String ACCESS_KEY = "OayadC4VrxKhmgOGECo6qkCnkxsbTdMum1GGxwc9";  
	public static String SECRET_KEY = "lLAz3sD2hj_zk67zXBE8h8f9etHZ4C3Hr1WERsBW";
	public static String bucketName = "cdkj-jj";
	public static String qiniuImgDomain = "http://oojgfm8c9.bkt.clouddn.com/";  //七牛图片地址域名
	private String callbackUrl;

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

}
