package com.cheng.shiro.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

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
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getdDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shrio过滤器
        /*
        * Shiro内置过滤器，可以实现权限相关的拦截器
        *   常用过滤器：
        *       anon:无需认证（登录）可以访问
        *       authc:必须认证才可以访问
        *       user:如果使用rememberMe的功能可以直接访问
        *       perms:该资源必须得到资源权限才可以使用
        *       role:该资源必须得到角色权限才可以访问
        * */

        Map<String,String> filterMap=new LinkedHashMap<String, String>();
        /*filterMap.put("/testController/add","authc");//使用authc拦截/add请求，进行权限认证
        filterMap.put("/testController/update","authc");*/
        //使用通配符设定目录下拦截
        filterMap.put("/testController/*","authc");//拦截testcontroller接口下的全部接口
        //处理testcontroller下不需要拦截的接口
        filterMap.put("/testController/toLogin","anon");

        //设置跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/testController/toLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
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