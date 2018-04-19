package top.liubowen.learning05.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;
import top.liubowen.learning05.entity.second.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/19 17:17
 * @description
 */
public interface SecondUserRepository extends JpaRepository<UserInfo, Long> {

}
