package org.youngtao.blog.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.utils.FileUtils;
import org.youngtao.blog.common.utils.LogUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 初始化国家化语言包到webapp下
 * 
 * @author linyantao
 * @date 2017年5月11日
 */
public class InitI18NListener implements ServletContextListener {

    private static final Logger LOG = LogUtils.get();

    @Override
    public void contextDestroyed(ServletContextEvent context) {

    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        try {

            String webappPath = context.getServletContext().getRealPath("/");
            String i18nPath = webappPath + Constants.PROPERTIES_WEBAPP_PATH;
            String jsPath = InitI18NListener.class.getResource(Constants.PROPERTIES_PATH).getPath();
            List<String> filepath = FileUtils.readListFileAbsolutes(jsPath);
            List<String> filepathName = FileUtils.readListFileNames(jsPath);
            for (int i = 0; i < filepath.size(); i++) {

                JSONObject jSONObject = new JSONObject();
                Properties prop = new Properties();
                FileInputStream in = new FileInputStream(filepath.get(i));
                BufferedReader bf = new BufferedReader(new InputStreamReader(in, Constants.GLOBAL_ENCODING));
                prop.load(bf);
                Set<Object> key = prop.keySet();
                for (Object object : key) {
                    String value = !StringUtils.isEmpty(object) ? prop.get(object).toString() : "";
                    jSONObject.put(object.toString(), value);
                }
                String propertiesName = filepathName.get(i);
                String i18NContent = createI18NFile(filepathName.get(i),jSONObject.toJSONString());
                FileUtils.createFile(i18nPath, propertiesName.substring(0,propertiesName.lastIndexOf(".")), i18NContent);
                bf.close();
                in.close();
            }
            LOG.info("Init JS i18n file end");
        } catch (Exception e) {

            LOG.error("Init i18n javascript exception {}", e);
        }
    }

    /**
     * 创建i18n国际化JS文件
     * 
     * @param bodyContent
     *            JSON内容
     * @throws IOException 
     */
    public String createI18NFile(String language,String bodyContent) throws IOException {

        String lineTxt = "";
        String lineContent = "";
        if(StringUtils.isEmpty(lineTxt)){

            String globalTemplatePath = InitI18NListener.class.getResource(Constants.TEMPLATE_PATH).getPath();
            ;
            File file = new File(globalTemplatePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), Constants.GLOBAL_ENCODING);
                BufferedReader bufferedReader = new BufferedReader(read);
                
                while ((lineContent = bufferedReader.readLine()) != null) {

                    lineTxt += lineContent;
                }
                read.close();
            }
        }
        
        String lang = language.substring(language.indexOf("_") + 1, language.lastIndexOf("."));
        lineTxt = lineTxt.replace(Constants.LANGUAGE_PLACEHOLDER, lang);
        lineTxt = lineTxt.replace(Constants.I18N_CONTENT_PLACEHOLDER, bodyContent);
        return lineTxt;
    }
}
