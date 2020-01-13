package com.forms.beneform4j.core.dao;

import java.util.List;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.dao.call.ICallResult;
import com.forms.beneform4j.core.dao.mybatis.MybatisDaoTemplate;
import com.forms.beneform4j.core.dao.mybatis.MybatisUtils;
import com.forms.beneform4j.core.dao.stream.IListStreamReader;
import com.forms.beneform4j.core.util.page.IPage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Dao操作静态帮助类，不提供事务操作API，而直接使用Spring管理事务<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class DaoUtils {

    /**
     * 获取平台表实际名称
     * 
     * @param tableName 原始表名
     * @return 添加平台配置的前缀后的表名
     */
    public static String getBeneform4jTableName(String tableName) {
        return Beneform4jConfig.getBeneform4jTablePrefix() + tableName;
    }

    /**
     * 查询单笔数据
     * 
     * @param sqlId SQL-ID
     * @return 单个对象
     */
    public static <T> T selectOne(String sqlId) {
        return getDaoTempate(sqlId).selectOne(sqlId);
    }

    /**
     * 查询单笔数据
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 单个对象
     */
    public static <T> T selectOne(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).selectOne(sqlId, parameter);
    }

    /**
     * 查询列表数据
     * 
     * @param sqlId SQL-ID
     * @return 对象列表
     */
    public static <E> List<E> selectList(String sqlId) {
        return getDaoTempate(sqlId).selectList(sqlId);
    }

    /**
     * 查询列表数据
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 对象列表
     */
    public static <E> List<E> selectList(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).selectList(sqlId, parameter);
    }

    /**
     * 查询分页列表数据
     * 
     * @param sqlId SQL-ID
     * @param page 分页对象
     * @return 指定页的对象列表
     */
    public static <E> List<E> selectList(String sqlId, IPage page) {
        return getDaoTempate(sqlId).selectList(sqlId, page);
    }

    /**
     * 查询分页列表数据
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @param page 分页对象
     * @return 指定页的对象列表
     */
    public static <E> List<E> selectList(String sqlId, Object parameter, IPage page) {
        return getDaoTempate(sqlId).selectList(sqlId, parameter, page);
    }

    /**
     * 流式查询
     * 
     * @param sqlId SQL-ID
     * @return 流式操作接口
     */
    public static <E> IListStreamReader<E> selectListStream(String sqlId) {
        return getDaoTempate(sqlId).selectListStream(sqlId);
    }

    /**
     * 流式查询
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 流式操作接口
     */
    public static <E> IListStreamReader<E> selectListStream(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).selectListStream(sqlId, parameter);
    }

    /**
     * 流式查询
     * 
     * @param sqlId SQL-ID
     * @param fetchSize 每次读取的记录条数(0, 5000]
     * @return 流式操作接口
     */
    public static <E> IListStreamReader<E> selectListStream(String sqlId, int fetchSize) {
        return getDaoTempate(sqlId).selectListStream(sqlId, fetchSize);
    }

    /**
     * 流式查询
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @param fetchSize 每次读取的记录条数(0, 5000]
     * @return 流式操作接口
     */
    public static <E> IListStreamReader<E> selectListStream(String sqlId, Object parameter, int fetchSize) {
        return getDaoTempate(sqlId).selectListStream(sqlId, parameter, fetchSize);
    }

    /**
     * 新增
     * 
     * @param sqlId SQL-ID
     * @return 影响的记录条数
     */
    public static int insert(String sqlId) {
        return getDaoTempate(sqlId).insert(sqlId);
    }

    /**
     * 新增
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 影响的记录条数
     */
    public static int insert(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).insert(sqlId, parameter);
    }

    /**
     * 修改
     * 
     * @param sqlId SQL-ID
     * @return 影响的记录条数
     */
    public static int update(String sqlId) {
        return getDaoTempate(sqlId).update(sqlId);
    }

    /**
     * 修改
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 影响的记录条数
     */
    public static int update(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).update(sqlId, parameter);
    }

    /**
     * 删除
     * 
     * @param sqlId SQL-ID
     * @return 影响的记录条数
     */
    public static int delete(String sqlId) {
        return getDaoTempate(sqlId).delete(sqlId);
    }

    /**
     * 删除
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 影响的记录条数
     */
    public static int delete(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).delete(sqlId, parameter);
    }

    /**
     * 执行批量：一个SQL执行多次
     * 
     * @param sqlId SQL-ID
     * @param parameters 参数对象数组
     * @return 批量执行的影响记录数组
     */
    public static int[] executeBatch(String sqlId, List<?> parameters) {
        return getDaoTempate(sqlId).executeBatch(sqlId, parameters);
    }

    /**
     * 执行批量：一次执行多个SQL
     * 
     * @param sqlIds 要执行的一组SQL-ID
     * @return 批量执行的影响记录数组
     */
    public static int[] executeBatch(List<String> sqlIds) {
        return getDaoTempate(sqlIds.get(0)).executeBatch(sqlIds);
    }

    /**
     * 执行批量：一次执行多个SQL
     * 
     * @param sqlIds 要执行的一组SQL-ID
     * @param parameters 参数对象数组
     * @return 批量执行的影响记录数组
     */
    public static int[] executeBatch(List<String> sqlIds, List<?> parameters) {
        return getDaoTempate(sqlIds.get(0)).executeBatch(sqlIds, parameters);
    }

    /**
     * 打开批量执行模式
     */
    public static void openBatchType() {
        MybatisUtils.openBatchType();
    }

    /**
     * 恢复打开批量模式之前的执行模式
     */
    public static void resetExecutorType() {
        MybatisUtils.resetExecutorType();
    }

    public static int[] flushBatch() {
        return MybatisDaoTemplate.staticFlushBatch();
    }

    /**
     * 调用存储过程
     * 
     * @param sqlId SQL-ID
     * @return 存储过程返回结果接口
     */
    public static ICallResult call(String sqlId) {
        return getDaoTempate(sqlId).call(sqlId);
    }

    /**
     * 调用存储过程
     * 
     * @param sqlId SQL-ID
     * @param parameter 参数对象
     * @return 存储过程返回结果接口
     */
    public static ICallResult call(String sqlId, Object parameter) {
        return getDaoTempate(sqlId).call(sqlId, parameter);
    }

    /**
     * 根据sqlId获取相对应的dao模板
     * 
     * @param sqlId
     * @return
     */
    private static IDaoTemplate getDaoTempate(String sqlId) {
        return MybatisUtils.getDaoTemplate(sqlId);
    }
}
