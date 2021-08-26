package com.seven.jdbc;


import cn.hutool.db.DbUtil;
import cn.hutool.db.ds.hikari.HikariDSFactory;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.seven.jdbc.bean.DbTable;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtilsTest {
    @Test
    public void getTablesMysql() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://10.202.83.203:3306/gstest?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("GSAdmin123");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        List<DbTable> dbTables = DBUtils.listTableInfo(dataSource);
//        System.out.println(JSONUtil.toJsonStr(dbTables));
//        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsInfo(dataSource, dbTables.get(0).getTableName())));
        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsBySql(dataSource,"select * from mysql_student_1,mysql_student_2")));

        Connection connection = dataSource.getConnection();
//        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsBySql(connection,"select * from all_com_key_getrelationtest")));
//        List<Map> tables = DBUtils.getTables(connection, null, null);
//        System.out.println(JSONUtil.toJsonStr(tables));
//        List<Map> fieldsWithTableName = DBUtils.getFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(fieldsWithTableName));
//        List<Map> keyFieldsWithTableName = DBUtils.getKeyFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(keyFieldsWithTableName));
//        List<Map> uniqueFieldsWithTableName = DBUtils.getUniqueFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(uniqueFieldsWithTableName));
//        connection.close();
    }

    @Test
    public void getTablesPg() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://10.201.81.131:5432/gservice?currentSchema=public&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("1qaz@WSX");
        hikariConfig.setSchema("public");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        List<DbTable> dbTables = DBUtils.listTableInfo(dataSource);
//        System.out.println(JSONUtil.toJsonStr(dbTables));
//        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsInfo(dataSource, dbTables.get(0).getTableName())));
        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsBySql(dataSource,"select * from graphx_instance_auto_backup,graphx_instance_backup_file")));
//        System.out.println(JSONUtil.toJsonStr(DBUtils.listTableInfo(dataSource)));
//        Connection connection = dataSource.getConnection();
//        List<Map> tables = DBUtils.getTables(connection, "public", null);
//        System.out.println(JSONUtil.toJsonStr(tables));
//        List<Map> fieldsWithTableName = DBUtils.getFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(fieldsWithTableName));
//        List<Map> keyFieldsWithTableName = DBUtils.getKeyFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(keyFieldsWithTableName));
//        List<Map> uniqueFieldsWithTableName = DBUtils.getUniqueFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(uniqueFieldsWithTableName));
//        connection.close();
    }


    @Test
    public void getTablesOracle() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@10.202.41.72:1521:helowin");
        hikariConfig.setUsername("system");
        hikariConfig.setPassword("gstest2021");
        hikariConfig.setSchema("SYSTEM");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        List<DbTable> dbTables = DBUtils.listTableInfo(dataSource);
        System.out.println(JSONUtil.toJsonStr(dbTables));
        System.out.println(JSONUtil.toJsonStr(DBUtils.listFieldsInfo(dataSource, dbTables.get(0).getTableName())));
//        Connection connection = dataSource.getConnection();
//        List<Map> tables = DBUtils.getTables(connection, "SYSTEM", null);
//        System.out.println(JSONUtil.toJsonStr(tables));
//        List<Map> fieldsWithTableName = DBUtils.getFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(fieldsWithTableName));
//        List<Map> keyFieldsWithTableName = DBUtils.getKeyFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(keyFieldsWithTableName));
//        List<Map> uniqueFieldsWithTableName = DBUtils.getUniqueFieldsWithTableName(connection, null, tables.get(0).get("TABLE_NAME").toString());
//        System.out.println(JSONUtil.toJsonStr(uniqueFieldsWithTableName));
//        connection.close();
    }

}