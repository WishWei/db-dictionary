package com.wish.dbdictionary.controller;

import com.wish.dbdictionary.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/api/v1.0/export/")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @RequestMapping("export.do")
    public void export(String url, String username, String password, String dbname, HttpServletResponse response) {
        try {

            response.setHeader("Content-type", "application/msword;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename=db.doc");
            OutputStream out = response.getOutputStream();
            exportService.doExport(url, username, password, dbname, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
