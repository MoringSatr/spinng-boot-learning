package top.liubowen.learning06.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liubowen
 * @date 2018/4/20 11:26
 * @description 通用mapper
 *              <p>
 *              特别注意，该接口不能mybatis被扫描到，否则会出错
 *              </p>
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
