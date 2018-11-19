package org.youngtao.blog.common.utils;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author linyantao
 *
 */
public class EncryptionUtils {

	public static String encryptionPwd(String str){
		
		return DigestUtils.sha1Hex(str);
	}
	
	public static void main(String[] args) {
		
		System.out.println(UUID.randomUUID().toString().replace("-", "").toUpperCase());
	}
}
