/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.ao;

import im.dadoo.teak.biz.bo.FileBO;
import im.dadoo.teak.biz.util.Util;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Component
public class FileAO {

  @Resource
  private FileBO qiniuFileBO;
	
	public Optional<String> save(MultipartFile file) throws IllegalStateException, IOException {
		if (file != null && !file.isEmpty()) {
		  String srcName = Util.createFileName();
		  File src = new File(srcName);
		  file.transferTo(src);
		  Optional<String> dst = this.qiniuFileBO.save(src);
		  src.delete();
		  return dst;
		}
		else {
			return Optional.absent();
		}
	}
}