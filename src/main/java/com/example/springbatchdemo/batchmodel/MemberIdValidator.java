package com.example.springbatchdemo.batchmodel;

import com.example.springbatchdemo.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MemberIdValidator implements ItemProcessor<Member, Member> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Member.class);

    @Override
    public Member process(Member member){
        String memberId= member.getId();
        if(memberId.length()==9 && memberId.startsWith("8")){
            return member;
        }
      return null;
    }


}
