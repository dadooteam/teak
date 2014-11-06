package im.dadoo.teak.biz.bo.impl;

import im.dadoo.teak.biz.bo.FileBO;

import java.io.File;


import org.json.JSONException;

import com.google.common.base.Optional;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

//在context中创建
public class QiniuFileBO implements FileBO {
  
  private Mac mac;
  private PutPolicy putPolicy;
  private String project;
  private String cdnUrl;
  
  public QiniuFileBO(Mac mac, PutPolicy putPolicy, String project, String cdnUrl) {
    this.mac = mac;
    this.putPolicy = putPolicy;
    this.project = project;
    this.cdnUrl = cdnUrl;
  }
  
  public Optional<String> save(File src) {
    try {
      if (src != null && src.exists()) {
        String uptoken = this.putPolicy.token(this.mac);
        PutExtra extra = new PutExtra();
        String dstPath = this.project + "/" + src.getName();
        PutRet ret = IoApi.putFile(uptoken, dstPath, src, extra);
        if (ret.ok()) {
          return Optional.of(this.cdnUrl + dstPath);
        } else {
          return Optional.absent();
        }
      } else {
        return Optional.absent();
      }
    } catch(AuthException | JSONException e) {
      e.printStackTrace();
      return Optional.absent();
    }
  }
}
