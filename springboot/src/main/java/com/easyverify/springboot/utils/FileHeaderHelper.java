package com.easyverify.springboot.utils;

import java.util.ArrayList;
import java.util.List;

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