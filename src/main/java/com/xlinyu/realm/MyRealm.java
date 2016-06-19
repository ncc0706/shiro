package com.xlinyu.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.xlinyu.domain.Permission;
import com.xlinyu.domain.Role;
import com.xlinyu.domain.User;
import com.xlinyu.service.IUserService;

/**
 * 自定义的指定Shiro验证用户登录的类
 */
@Component
public class MyRealm extends AuthorizingRealm {

	@Resource
	private IUserService userService;
	
	private static final Logger logger = Logger.getLogger(MyRealm.class);
	
	/**
	 * 为当前登录的Subject授予角色和权限
	 * 本例中该方法的调用时机为需授权资源被访问时
	 * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String username = (String) principals.getPrimaryPrincipal();
		logger.info("currentUsername:" + username);
		
		User user =  userService.findUserByUsername(username);
		//System.out.println(user);
		List<String> roleList= new ArrayList<String>();
		List<String> permList = new ArrayList<String>();
		System.out.println("对当前用户：["+username+"]进行授权！");
		if(null != user){
			if(user.getRoles() != null && user.getRoles().size() > 0){
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				for(Role role: user.getRoles()){
					roleList.add(role.getName());
					if(role.getPermissions() != null && role.getPermissions().size() > 0){
						for(Permission per:role.getPermissions()){
							permList.add(per.getName());
						}
					}
				}
				authorizationInfo.addRoles(roleList);
				authorizationInfo.addStringPermissions(permList);
				return authorizationInfo;
			}
		}else {
			//throw new AuthorizationException();
			
			
		}
		return null;
	}

	/**
	 * 验证当前登录的Subject
	 * 该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		
		 User user = userService.findUserByUsername(token.getUsername());
		 if(null != user){
			 System.out.println(this.getName());
	         AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());  
	         this.setSession("currentUser", user.getUsername());  
	         return authcInfo;  
	      }else{  
	          return null;  
	      }
		
		/*if("admin".equals(token.getUsername())){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("admin", "admin", this.getName());  
            this.setSession("currentUser", "admin");  
            return authcInfo;  
        }else if("zhangsan".equals(token.getUsername())){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("zhangsan", "zhangsan", this.getName());  
            this.setSession("currentUser", "zhangsan");  
            return authcInfo;  
        }*/
		
		//没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
		//return null;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

    //setc
}
