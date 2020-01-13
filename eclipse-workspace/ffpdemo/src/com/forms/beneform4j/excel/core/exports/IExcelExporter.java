package com.forms.beneform4j.excel.core.exports;

import java.io.OutputStream;

import com.forms.beneform4j.excel.core.model.em.IEM;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Excel导出接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2017-2-16<br>
 */
public interface IExcelExporter {

    /**
     * 导出数据至Excel文件输出流
     * 
     * @param modelId Excel模型ID
     * @param data 数据，在文件模板中可通过data.property的形式来访问数据属性，在xml配置的树型模板中可以直接访问data的属性（下同）
     * @param output 输出流
     */
    public void exports(String modelId, Object data, OutputStream output);

    /**
     * 导出数据至Excel文件
     * 
     * @param modelId Excel模型ID
     * @param data 数据
     * @param filename 输出文件名
     * @return 实际输出文件名，如果是Excel文件模板，则返回文件名后缀和模板相同，否则返回文件名后缀为xlsx（下同）
     */
    public String exports(String modelId, Object data, String filename);

    /**
     * 根据参数导出数据至Excel文件输出流
     * 
     * @param modelId Excel模型ID
     * @param param 参数，在文件模板和动态模板中可以通过param.property的形式访问，在xml配置的树型模板中可以通过#param.property的形式访问（下同）
     * @param data 数据
     * @param output 输出流
     */
    public void exports(String modelId, Object param, Object data, OutputStream output);

    /**
     * 根据参数导出数据至Excel文件
     * 
     * @param modelId Excel模型ID
     * @param param 参数
     * @param data 数据
     * @param filename 输出文件名
     * @return 实际输出文件名
     */
    public String exports(String modelId, Object param, Object data, String filename);

    /**
     * 导出数据至Excel文件输出流
     * 
     * @param model Excel模型
     * @param data 数据
     * @param output 输出流
     */
    public void exports(IEM model, Object data, OutputStream output);

    /**
     * 导出数据至Excel文件
     * 
     * @param model Excel模型
     * @param data 数据
     * @param filename 输出文件名
     * @return 实际输出文件名
     */
    public String exports(IEM model, Object data, String filename);

    /**
     * 根据参数导出数据至Excel文件输出流
     * 
     * @param model Excel模型
     * @param param 参数
     * @param data 数据
     * @param output 输出流
     */
    public void exports(IEM model, Object param, Object data, OutputStream output);

    /**
     * 根据参数导出数据至Excel文件
     * 
     * @param model Excel模型
     * @param param 参数
     * @param data 数据
     * @param filename 输出文件名
     * @return 实际输出文件名
     */
    public String exports(IEM model, Object param, Object data, String filename);
}
