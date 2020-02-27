package com.cheng.shiro.shiro;

import com.cheng.shiro.entity.User;
import com.cheng.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/2/24
 * Time: 23:21
 * Description: No Description
 *
 * 自定义realm
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        //管理用户授权访问
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //添加授权字符串

        //获取当前用户权限
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        User user = userService.queryUserById(principal.getId());
        String perms = user.getPerms();
        authorizationInfo.addStringPermission(perms);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //执行
        System.out.println("执行认证逻辑");
        /*//认证账户密码
        //模拟
        String user="lijincheng";
        String pass="123";*/

        //mybatis获取数据库信息

        //获取前台获取的账号密码
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByName(usernamePasswordToken.getUsername());

        System.out.println(usernamePasswordToken.getUsername());
        if (user==null){
            //用户不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPass(),"");
    }
}
