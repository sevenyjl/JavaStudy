package com.seven.jdbc;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.handler.BeanListHandler;
import com.seven.jdbc.bean.DbField;
import com.seven.jdbc.bean.DbTable;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DBUtils {
    /**
     * 获取表信息
     */
    public static List<Map> getTables(@NonNull Connection connection, String schema, String tableName) throws SQLException {
        schema = initDefault(schema);
        tableName = initDefault(tableName);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tablesResultSet = metaData.getTables(connection.getCatalog(), schema, tableName, new String[]{"TABLE"});
        return handlerResultSet(tablesResultSet);
    }

    private static String initDefault(String schema) {
        if (StrUtil.isEmpty(schema)) {
            return "%";
        }
        return schema;
    }

    /**
     * 更具表获取字段信息
     */
    public static List<Map> getFieldsWithTableName(@NonNull Connection connection, String schema, @NonNull String tableName) throws SQLException {
        schema = initDefault(schema);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columnsResultSet = metaData.getColumns(connection.getCatalog(), schema, tableName, "%");
        return handlerResultSet(columnsResultSet);
    }

    /**
     * 根据表名获取当前表的主键
     */
    public static List<Map> getKeyFieldsWithTableName(@NonNull Connection connection, String schema, @NonNull String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), schema, tableName);
        return handlerResultSet(primaryKeys);
    }

    /**
     * 获取唯一键
     *
     * @return
     * @throws SQLException
     */
    public static List<Map> getUniqueFieldsWithTableName(@NonNull Connection connection, String schema, @NonNull String tableName) throws SQLException {
        return getIndexFieldsWithTableName(connection, schema, tableName, true, false);
    }

    /**
     * 获取索引
     *
     * @param connection
     * @param schema
     * @param tableName
     * @param unique      该参数为 true 时，仅返回惟一值的索引；该参数为 false 时，返回所有索引，不管它们是否惟一
     * @param approximate 该参数为 true 时，允许结果是接近的数据值或这些数据值以外的值；该参数为 false 时，要求结果是精确结果
     * @return
     * @throws SQLException
     */
    public static List<Map> getIndexFieldsWithTableName(@NonNull Connection connection, String schema, @NonNull String tableName, boolean unique, boolean approximate) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getIndexInfo(connection.getCatalog(), schema, tableName, unique, approximate);
        return handlerResultSet(primaryKeys);
    }

    private static List<Map> handlerResultSet(ResultSet resultSet) throws SQLException {
        try {
            List<Map> handle = new BeanListHandler<>(Map.class).handle(resultSet);
            return handle.stream().map(s -> {
                HashMap<Object, Object> temp = new HashMap<>();
                s.forEach((k, v) -> temp.put(k.toString().toUpperCase(), v));
                return temp;
            }).collect(Collectors.toList());
        } finally {
            DbUtil.close(resultSet);
        }
    }

    /**
     * 获取表信息
     *
     * @return
     */
    public static List<DbTable> listTableInfo(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            if (dataSource instanceof HikariDataSource) {
                HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                List<Map> tables = getTables(connection, hikariDataSource.getSchema(), "%");
                return DbTable.map2Bean(tables);
            } else {
                throw new RuntimeException("不支持连接池类型：" + dataSource.getClass().getName());
            }
        } finally {
            DbUtil.close(connection);
        }
    }

    /**
     * 根据表名获取 字段信息
     *
     * @param dataSource
     * @return
     */
    public static List<DbField> listFieldsInfo(DataSource dataSource, String tableName) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            if (dataSource instanceof HikariDataSource) {
                HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                List<Map> tables = getFieldsWithTableName(connection, hikariDataSource.getSchema(), tableName);
                List<DbField> dbFields = DbField.map2Bean(tables);
                HashMap<String, DbField> temp = new HashMap<>();
                dbFields.forEach(dbField -> temp.put(dbField.getColumnName(), dbField));
                getUniqueFieldsWithTableName(connection, hikariDataSource.getSchema(), tableName).forEach(s -> {
                    String columnName = MapUtil.getStr(s, "COLUMN_NAME");
                    DbField dbField = temp.get(columnName);
                    if (dbField == null) {
                        log.error("错误唯一键：{},字段信息：{}", columnName, dbFields);
                    } else {
                        dbField.setIsUnique(true);
                    }
                });
                getKeyFieldsWithTableName(connection, hikariDataSource.getSchema(), tableName).forEach(s -> {
                    String columnName = MapUtil.getStr(s, "COLUMN_NAME");
                    DbField dbField = temp.get(columnName);
                    if (dbField == null) {
                        log.error("错误主键：{},字段信息：{}", columnName, dbFields);
                    } else {
                        dbField.setIsKey(true);
                    }
                });
                return new ArrayList<>(temp.values());
            } else {
                throw new RuntimeException("不支持连接池类型：" + dataSource.getClass().getName());
            }
        } finally {
            DbUtil.close(connection);
        }
    }
}
