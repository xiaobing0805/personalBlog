package org.youngtao.blog.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @author Young tao
 *
 */
public class JacksonUtils {


	/** 
     * 对象转json 
     * @param obj object 对象 
     * @return String 
     * @throws IOException 
     */  
    public static String BeanToJson(Object obj) throws IOException {   
          
        ObjectMapper mapper = new ObjectMapper();    
        StringWriter sw = new StringWriter();    
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);    
        mapper.writeValue(gen, obj);    
        gen.close();    
        return sw.toString();    
    }
    
    /** 
     * json转对象 
     * @param jsonStr json字符串 
     * @param objClass 类名.class 
     * @return 泛型 
     * @throws Exception 
     */  
    public static <T> T jsonToBean(String jsonStr, Class<T> objClass)    
            throws Exception {  
          
        ObjectMapper mapper = new ObjectMapper();   
          
        return mapper.readValue(jsonStr, objClass);    
    }
}
