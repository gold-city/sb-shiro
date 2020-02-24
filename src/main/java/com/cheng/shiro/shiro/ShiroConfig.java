package com.cheng.shiro.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//shiro权限框架配置类
@Configuration//申明改类为spring配置类
public class ShiroConfig {

    /*shiro的核心api
    *
    * subject：用户主体（吧操作交给securityManager）
    * securityManager:安全管理器（关联realm）
    * realm:Shiro链接数据库的桥梁
    * */


    /*
    * 创建ShrioFilterFactoryBean
    * */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getdDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }



    /*
    * 创建DefaultWebSecurityManager
    * */
    @Bean
    public DefaultWebSecurityManager getdDefaultWebSecurityManager(@Autowired/*@Qualifier("getMyRealm")*//*自动注入id为getMyRealm返回的myrelalm*/ MyRealm myRealm1){
        //这个类本身是配置类，可以往ioc存放bean，如果这里添加bean时用到别的bean，可以直接new，也可以使用ioc容器中的bean，因为这里使用的并始终只实例化一次
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm1);
        return securityManager;
    }


    /*
    * 创建Realm
    * */
    @Bean
    public MyRealm getMyRealm(){//这里通过注解注入bean，该注解的id为方法名getMyRealm(返回值的小写开头)
        return new MyRealm();
    }
}