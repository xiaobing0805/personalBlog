package org.youngtao.blog.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.youngtao.blog.common.constant.Constants;

public class FileUtils {

    private static final Logger LOG = LogUtils.get();

    /***
     * 获取指定目录下的所有的文件（不包括文件夹），采用了递归
     * 
     * @param obj
     * @return
     */
    public static ArrayList<String> readListFileAbsolutes(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        ArrayList<String> files = new ArrayList<String>();
        if (directory.isFile()) {

            String path = directory.getPath();
            if (Constants.SUFFIX_PROPERTIES.equals(path.substring(path.lastIndexOf(".") + 1, path.length()))) {
                files.add(directory.getPath());
            }
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File fileOne = fileArr[i];
                files.addAll(readListFileAbsolutes(fileOne));
            }
        }
        return files;
    }

    /***
     * 获取指定目录下的所有的文件名，采用了递归
     * 
     * @param obj
     * @return
     */
    public static ArrayList<String> readListFileNames(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        ArrayList<String> files = new ArrayList<String>();
        if (directory.isFile()) {
            String path = directory.getPath();
            if (Constants.SUFFIX_PROPERTIES.equals(path.substring(path.lastIndexOf(".") + 1, path.length()))) {
                files.add(directory.getName());
            }
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File fileOne = fileArr[i];
                files.addAll(readListFileNames(fileOne));
            }
        }
        return files;
    }

    /**
     * 创建文件
     * 
     * @param path
     *            文件存放路径
     * @param fileName
     *            文件名
     * @param filecontent
     *            文件内容
     * @return
     */
    public static void createFile(String path, String fileName, String filecontent) {
        String filenameTemp = path + fileName + ".js";// 文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            // 如果文件不存在，则删除
            if (file.exists()) {

                file.delete();
            }
            file.createNewFile();
            // 创建文件成功后，写入内容到文件里
            writeFileContent(filenameTemp, filecontent);
            LOG.info("success create file && write content succcess {}", filenameTemp);
        } catch (Exception e) {

            LOG.error("success create file && write content exception {}", e);
        }
    }

    /**
     * 向文件中写入内容
     * 
     * @param filepath
     *            文件路径与名称
     * @param newstr
     *            写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";// 新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);// 文件路径(包括文件名称)
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            // 文件原有内容
            for (; (temp = br.readLine()) != null;) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            // 不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }
}
