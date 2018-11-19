package org.youngtao.blog.common.utils;

import org.youngtao.blog.common.domain.Result;

public final class JsonResultUtils {

	public static <T> Result<T> getJsonResult(T data,Boolean successFul,String errorCode,String errorMessage){
		
		Result<T> reslt = new Result<T>();
		
		reslt.setSuccessFul(successFul);
		reslt.setData(data);
		reslt.setErrorCode(errorCode);
		reslt.setErrorMessage(errorMessage);
		reslt.setTimestamp(System.currentTimeMillis() + "");
		
		return reslt;
	}
}
