package com.zhliang.springbootmysqlwr.service;
import	java.util.ArrayList;
import com.zhliang.springbootmysqlwr.annotation.Master;
import com.zhliang.springbootmysqlwr.dao.MemberMapper;
import com.zhliang.springbootmysqlwr.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: colin
 * @Date: 2019/10/18 11:39
 * @Description:
 * @Version: V1.0
 */
@Service
public class MemberService {

    //@Autowired
    //private MemberMapper memberMapper;

    private static Map<String,Member> IocMember = new HashMap<>();

    @Transactional
    public int insert(Member member) {
        IocMember.put(member.getName(),member);
        return 1;
        //return memberMapper.insert(member);
    }

    @Master
    public int save(Member member) {
        IocMember.put(member.getName(),member);
        return 1;
    }

    public List<Member> selectAll() {
        Set<String> keySet = IocMember.keySet();
        List<Member> list = new ArrayList<> ();
        for (String key : keySet) {
            list.add(IocMember.get(key));
        }
        return list;
        //return memberMapper.selectByMember(new Member());
    }

    @Master
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}
