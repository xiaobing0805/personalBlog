package org.youngtao.blog.permission.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.common.utils.EncryptionUtils;
import org.youngtao.blog.common.utils.LogUtils;
import org.youngtao.blog.data.domain.user.Permissions;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.data.domain.user.UserSettings;
import org.youngtao.blog.permission.session.SessionUtils;
import org.youngtao.blog.service.user.PermissionsService;
import org.youngtao.blog.service.user.UserService;
import org.youngtao.blog.service.user.UserSettingsService;


/**
 * 
 * @author Young Tao
 *
 */
public class UserRealm extends AuthorizingRealm{
	
	private static Logger LOG = LogUtils.get();

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSettingsService userSettingsService;
	
	private Cache<String, AtomicInteger> passwordRetryCache;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private PermissionsService permissionsService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = SessionUtils.getUserSession();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(username));
        List<Permissions> permissions = permissionsService.findPermissionsByUserId(user.getId());
        Set<String> set = new HashSet<String>();
        for (Permissions ps : permissions) {
            if(Constants.CONTROLLER_STATUS.equals(ps.getPmsOrCtl())){
                set.add(ps.getPermissionsName());
            }
        }
        authorizationInfo.setStringPermissions(set);

        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws UnknownAccountException {
		
		String username = (String)token.getPrincipal();

        doCredentialsMatch(username); //认证用户是否大于限制次数
		
		String password = new String((char[])token.getCredentials());

        User user = userService.getUserByUsername(username);
        
        if(user == null){
        	
        	throw new AccountException();
        }
		
		String pwd = EncryptionUtils.encryptionPwd(password + user.getSalt());
		if(!user.getPassword().equals(pwd)){
			
			throw new IncorrectCredentialsException();
		}
		
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		username, //用户名
        		user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
		List<UserSettings> us = userSettingsService.findByUserId(user.getId());
		SessionUtils.setUserSession(user); //将用户信息Session
		SessionUtils.cacheUserSettings(us); //将用户配置信息缓存进session
		passwordRetryCache.remove(username);
        try {
			userService.updateSessionId(user.getId(), SecurityUtils.getSubject().getSession().getId().toString());
		} catch (BusinessException e) {
			
			LOG.error("Realm update user session id failed exception is {}",e);
		}
        return authenticationInfo;
	}
	
	public void doCredentialsMatch(String username) {

		if(passwordRetryCache == null) passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	            
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 3) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

    }

	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
			throws AuthenticationException {
		
		/**
		 * 此处是使用shiro自带的加密方法做验证，我使用的是自己的加密认证方法，所以重写屏蔽掉
		 */
	}

	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
    
    public void removeUserAuthorizationInfoCache(String username) {  
        SimplePrincipalCollection pc = new SimplePrincipalCollection();  
        pc.add(username, super.getName());  
        super.clearCachedAuthorizationInfo(pc);  
    } 
	
}
