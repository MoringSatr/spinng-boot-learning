package top.liubowen.learning02.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import top.liubowen.learning02.dao.UserDao;
import top.liubowen.learning02.entity.UserInfo;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author liubowen
 * @date 2018/4/18 12:17
 * @description
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserInfo> fetchUsers() {
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public UserInfo findUser(long userId) {
        String sql = "SELECT * FROM USER WHERE `id` =?";
        return jdbcTemplate.queryForObject(sql, new Object[] { userId }, rowMapper);
    }

    @Override
    public boolean save(UserInfo userInfo) {
        String sql = "INSERT INTO USER( `name`, `age`) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator statementCreator = (conn) -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id" }); // 指定 id 为主键
            ps.setString(1, userInfo.getName());
            ps.setInt(2, userInfo.getAge());
            return ps;
        };
        int update = jdbcTemplate.update(statementCreator, keyHolder);
        userInfo.setId(Long.parseLong(keyHolder.getKey().toString()));
        return update == 1;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        String sql = "UPDATE USER SET `name` =?, `age` =? WHERE `id` =?";
        return jdbcTemplate.update(sql, new Object[] { userInfo.getName(), userInfo.getAge(), userInfo.getId() }) == 1;
    }

    @Override
    public boolean delete(long userId) {
        String sql = "DELETE FROM USER WHERE `id` =?";
        return jdbcTemplate.update(sql, new Object[] { userId }) == 1;
    }
}
