package com.wish.dbdictionary.handler;

import com.wish.dbdictionary.dto.ColumnInfoDTO;
import com.wish.dbdictionary.dto.TableInfoDTO;
import com.wish.dbdictionary.exception.CommonException;
import com.wish.dbdictionary.util.Dialect;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class ExportHandler {

    public static List<TableInfoDTO> gettableCls(String url, String username, String password, String dbName, Dialect dialect)
    {
        log.info("开始读取");
        try {
            Class.forName(dialect.getDriver());
        } catch (ClassNotFoundException e) {
            log.error("加载驱动失败");
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    url, username, password);
            List<TableInfoDTO> tableInfos = getTableList(connection, dbName, dialect);
            getColomnList(connection, tableInfos, dbName, dialect);
            return tableInfos;
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new CommonException("获取表结构失败");
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private static List<TableInfoDTO> getTableList(Connection connection, String dbName, Dialect dialect) throws SQLException{
        List<TableInfoDTO> tableInfos = new ArrayList<TableInfoDTO>();
        PreparedStatement ps = connection.prepareStatement(dialect.getTableListSql());
        ps.setString(1, dbName);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            TableInfoDTO tableInfo = new TableInfoDTO();
            tableInfo.setTname(rs.getString(1));
            tableInfo.setComments(rs.getString(2));
            tableInfos.add(tableInfo);
        }
        rs.close();
        ps.close();
        return tableInfos;
    }

    private static void getColomnList(Connection connection, List<TableInfoDTO> tableInfos, String dbName, Dialect dialect) throws SQLException{
        ResultSet rs = null;
        PreparedStatement ps = connection.prepareStatement(dialect.getTableColumnSql());
        int i = 0;
        ps.setString(1, dialect.upperCase() ? dbName.toUpperCase() : dbName.toLowerCase());
        for (TableInfoDTO tableInfo : tableInfos) {
            log.info("读取{}：{}/{}", tableInfo.getTname(), ++i, tableInfos.size());
            List list = new ArrayList<ColumnInfoDTO>();
            ps.setString(2, dialect.upperCase() ? tableInfo.getTname().toUpperCase() : tableInfo.getTname().toLowerCase());
            rs = ps.executeQuery();
            while(rs.next())
            {
                ColumnInfoDTO c = new ColumnInfoDTO();
                c.setColumnName(rs.getString(1));
                c.setType(rs.getString(2));
                c.setLength(rs.getLong(3));
                c.setNullable(rs.getString(4));
                c.setComments(rs.getString(5));
                list.add(c);
            }
            tableInfo.setColumns(list);
        }
        rs.close();
        ps.close();
        log.info("读取完毕");
    }
}
