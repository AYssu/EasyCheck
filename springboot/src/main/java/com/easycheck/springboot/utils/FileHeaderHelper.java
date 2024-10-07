package com.easycheck.springboot.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 读取文件流头信息
 *
 * @author hanjie
 */
public class FileHeaderHelper {

    private static FileHeaderHelper me;
    private static List<String> headerList;

    private FileHeaderHelper() {
    }

    public static FileHeaderHelper getInstance() {
        if (me == null) {
            me = new FileHeaderHelper();
        }
        return me;
    }

    public List<String> getHeaderList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add("FFD8FF");
        headerList.add("89504E47");
        return headerList;
    }

    public List<String> getBinList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add("7F454C4602010100");
        headerList.add("504B0304");
        return headerList;
    }
}