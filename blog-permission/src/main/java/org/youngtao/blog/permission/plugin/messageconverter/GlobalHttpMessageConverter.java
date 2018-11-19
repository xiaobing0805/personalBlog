package org.youngtao.blog.permission.plugin.messageconverter;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.youngtao.blog.common.utils.JsonResultUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 返回统一json格式
 * @author linyantao
 *
 */
public class GlobalHttpMessageConverter extends FastJsonHttpMessageConverter {
	
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
			
		obj = JsonResultUtils.getJsonResult(obj, true, null, null);
		outputMessage.getHeaders();
		OutputStream out = outputMessage.getBody();
		String text = JSON.toJSONString(obj,getFeatures());
		byte[] bytes = text.getBytes(getCharset());
		
		out.write(bytes);
	}

	
}
