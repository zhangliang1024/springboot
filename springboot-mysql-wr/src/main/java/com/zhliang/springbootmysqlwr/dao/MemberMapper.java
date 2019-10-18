package com.zhliang.springbootmysqlwr.dao;

import com.zhliang.springbootmysqlwr.pojo.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/10/18 11:43
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface MemberMapper {
    
    int insert(Member member);

    List<Member> selectByMember(Member member);
}
