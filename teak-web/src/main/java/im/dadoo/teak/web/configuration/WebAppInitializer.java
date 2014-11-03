/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.web.configuration;

import im.dadoo.teak.biz.configuration.TeakCoreContext;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author codekitten
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {TeakWebContext.class,TeakCoreContext.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
  
  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter ceFilter = new CharacterEncodingFilter();
    ceFilter.setEncoding("UTF-8");
    ceFilter.setForceEncoding(true);

    HiddenHttpMethodFilter hhmFilter = new HiddenHttpMethodFilter();
    return new Filter[]{ceFilter, hhmFilter};
  }
  
}
