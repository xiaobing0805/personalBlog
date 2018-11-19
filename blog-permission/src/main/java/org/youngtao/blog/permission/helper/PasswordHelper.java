package org.youngtao.blog.permission.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
    private static String algorithmName = "SHA-1";
    private static int hashIterations = 2;
    
    public static String generatorSalt(){
    	
    	return randomNumberGenerator.nextBytes().toHex();
    }
    
    public static String generatorPasword (String pwd,String salt){
    	
    	String password = new SimpleHash(
                algorithmName,
                pwd,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();
    	return password;
    }
    
}
