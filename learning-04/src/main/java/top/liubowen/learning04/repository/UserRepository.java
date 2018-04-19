package top.liubowen.learning04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.liubowen.learning04.entity.UserInfo;

/**
 * @author liubowen
 * @date 2018/4/19 17:17
 * @description
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> {

}
