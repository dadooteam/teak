package im.dadoo.teak.biz.bo;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

@Component
public class QiniuFileBO {
  
  public boolean save(String filename, String localFilePath) throws AuthException, JSONException {
    Config.ACCESS_KEY = "K_sLxJjKPoms2DrECw-1FqRjxpjPFVLYTI2q6-Ue";
    Config.SECRET_KEY = "n5ne9ImpHwpvbg_v5cy7w8OL7h1t-xth3oFQLq1z";
    Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
    String bucketName = "dadoo";
    PutPolicy putPolicy = new PutPolicy(bucketName);
    String uptoken = putPolicy.token(mac);
    PutExtra extra = new PutExtra();
    PutRet ret = IoApi.putFile(uptoken, filename, localFilePath, extra);
    return ret.ok();
  }
}
