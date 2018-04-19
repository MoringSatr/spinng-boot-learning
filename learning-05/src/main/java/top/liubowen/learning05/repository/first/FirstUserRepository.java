package top.liubowen.learning05.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import top.liubowen.learning05.entity.first.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/19 17:17
 * @description
 */
public interface FirstUserRepository extends JpaRepository<UserInfo, Long> {

}
