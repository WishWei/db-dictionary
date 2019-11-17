package com.wish.dbdictionary.service.impl;

import com.wish.dbdictionary.dto.TableInfoDTO;
import com.wish.dbdictionary.handler.ExportHandler;
import com.wish.dbdictionary.service.ExportService;
import com.wish.dbdictionary.util.DbTypeUtil;
import com.wish.dbdictionary.util.Dialect;
import com.wish.dbdictionary.util.DialectFactory;
import com.wish.dbdictionary.util.ExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.dbname}")
    private String dbname;
    @Value("${exportPath}")
    private String exportPath;

    @Override
    public void doExport() {
        String dbType = DbTypeUtil.getDateType(url);
        Dialect dialect = DialectFactory.getDialect(dbType);
        List<TableInfoDTO> tableInfoList = ExportHandler.gettableCls(url, username, password, dbname, dialect);
        try {
            ExportUtil.exportToWord(exportPath, tableInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
