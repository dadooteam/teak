/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.configuration;

import im.dadoo.teak.biz.bo.FileBO;
import im.dadoo.teak.biz.bo.impl.QiniuFileBO;

import com.alibaba.druid.pool.DruidDataSource;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author codekitten
 */
@Configuration
@PropertySource("file:C:/config/teak/config.properties")
@ComponentScan("im.dadoo.teak.biz")
public class TeakBizContext {
  
  @Resource
  private Environment env;
  
  @Bean(initMethod = "init", destroyMethod = "close")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(this.env.getProperty("db.url"));
    dataSource.setUsername(this.env.getProperty("db.username"));
    dataSource.setPassword(this.env.getProperty("db.password"));
    return dataSource;
  }
  
  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate() {
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
    return jdbcTemplate;
  }
  
  @Bean
  public FileBO qiniuFileBO() {
    Config.ACCESS_KEY = this.env.getProperty("qiniu.access_key");
    Config.SECRET_KEY = this.env.getProperty("qiniu.secret_key");
    Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
    PutPolicy putPolicy = new PutPolicy(this.env.getProperty("qiniu.bucket"));
    return new QiniuFileBO(mac, putPolicy, this.env.getProperty("qiniu.project"), this.env.getProperty("qiniu.cdn_url"));
  }
}
