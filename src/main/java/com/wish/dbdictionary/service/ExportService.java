package com.wish.dbdictionary.service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public interface ExportService {
//    /**
//     * 导出数据字典
//     */
//    void doExport();

    /**
     * 导出数据字典
     * @param url
     * @param username
     * @param password
     * @param dbname
     */
    void doExport(String url, String username, String password, String dbname, OutputStream out);
}
