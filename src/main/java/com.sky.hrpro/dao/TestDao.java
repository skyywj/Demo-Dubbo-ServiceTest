package com.sky.hrpro.dao;

import com.sky.hrpro.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CarryJey
 * @Date: 2018/9/27 上午10:29
 */
@Repository
@CacheConfig(cacheNames = "test_cache")
public class TestDao {
    private static final BeanPropertyRowMapper<TestEntity> rowMapper = BeanPropertyRowMapper.newInstance(TestEntity.class);

    /**
     * 切记：这里用的是NamedParameterJdbcTemplate
     * 而不是JdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


//    @CachePut(key = "'test_id_'+#id")
    public void addTest(int id){
        String sql = "INSERT INTO test (id,name,age) VALUES (:id,'ywj',23)";
        int rows = jdbcTemplate.update(sql,new MapSqlParameterSource("id",id));
        assert rows == 1;
    }

    @Cacheable(key = "'test_id_' + #id")
    public TestEntity getTest(int id){
        String sql = "SELECT id,name,age FROM test WHERE id =:id";
        List<TestEntity> list = jdbcTemplate.query(sql, new MapSqlParameterSource("id", id),rowMapper);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


}
