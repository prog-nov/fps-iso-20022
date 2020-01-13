package com.forms.beneform4j.util.toolimpl;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;

import com.forms.beneform4j.core.util.depends.IDependNode;
import com.forms.beneform4j.core.util.depends.impl.DependGraph;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 集合工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class CollectionUtilsImpl {

    private static final CollectionUtilsImpl instance = new CollectionUtilsImpl() {};

    private CollectionUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static CollectionUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 依赖排序，用于给一组指定顺序或依赖的数据排序
     * 
     * @param nodeList 需要排序的节点集，节点集中的元素类型需要是IDependNode或其子类型
     * @return 已经根据依赖、顺序排好序的节点集
     */
    public <E extends IDependNode> List<E> sortDependNodes(List<E> nodeList) {
        return new DependGraph<E>(nodeList).sort();
    }

    /**
     * 将对象添加到对象数组中
     * 
     * @param array 目标数组
     * @param obj 要添加到数组的对象
     * @return 新数组
     */
    public <A, O extends A> A[] addObjectToArray(A[] array, O obj) {
        Class<?> compType = Object.class;
        if (array != null) {
            compType = array.getClass().getComponentType();
        } else if (obj != null) {
            compType = obj.getClass();
        }
        int newArrLength = (array != null ? array.length + 1 : 1);
        @SuppressWarnings("unchecked")
        A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
        if (array != null) {
            System.arraycopy(array, 0, newArr, 0, array.length);
        }
        newArr[newArr.length - 1] = obj;
        return newArr;
    }

    /**
     * 复制列表
     * 
     * @param dest 目标集合
     * @param src 源集合
     */
    public <T> void copy(List<? super T> dest, List<? extends T> src) {
        Collections.copy(dest, src);
    }
}
