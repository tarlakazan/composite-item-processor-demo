package com.example.springbatchdemo.batchmodel;

import com.example.springbatchdemo.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MemberNameMerger implements ItemProcessor<Member, Member> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberNameMerger.class);
    @Override
    public Member process(Member member){
      member.setFullName(member.getFirstName().concat(" ").concat(member.getLastName()));
      return member;

    }
  }
