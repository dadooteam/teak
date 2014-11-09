/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.controller;

import im.dadoo.teak.web.ao.FileAO;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;

/**
 *
 * @author codekitten
 */
@Controller
public class MediaController {
  
  @Resource
  private FileAO fileService;
	
  private static final String CKEDITOR_CALLBACK = 
          "<script type='text/javascript'>"
          + "window.parent.CKEDITOR.tools.callFunction(%d, '%s', '%s');"
          + "</script>";
  
	@RequestMapping(value = "/api/upload/media", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam Integer CKEditorFuncNum, @RequestParam MultipartFile upload) 
          throws IllegalStateException, IOException {
    Optional<String> path = this.fileService.save(upload);
    String result = "";
    if (path.isPresent()) {
      result = String.format(CKEDITOR_CALLBACK, CKEditorFuncNum, path.get(), "上传成功");
    } else {
      result = String.format(CKEDITOR_CALLBACK, CKEditorFuncNum, path.orNull(), "上传失败");
    }
    return result;
  }
}
