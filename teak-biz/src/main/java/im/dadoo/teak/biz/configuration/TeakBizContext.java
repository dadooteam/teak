/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.teak.biz.configuration;

import com.alibaba.druid.pool.DruidDataSource;

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

}
