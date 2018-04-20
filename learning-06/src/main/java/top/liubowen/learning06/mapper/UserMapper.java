package top.liubowen.learning06.mapper;

import org.springframework.stereotype.Repository;
import top.liubowen.learning06.common.CommonMapper;
import top.liubowen.learning06.entity.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/20 11:26
 * @description
 */
@Repository
public interface UserMapper extends CommonMapper<UserInfo> {
}
