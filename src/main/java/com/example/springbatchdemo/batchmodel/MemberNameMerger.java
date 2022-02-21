package com.example.springbatchdemo.batchmodel;

import com.example.springbatchdemo.domain.Member;
import org.springframework.batch.item.ItemProcessor;

public class MemberNameMerger implements ItemProcessor<Member, Member> {

    @Override
    public Member process(Member member){
      member.setFullName(member.getFirstName().concat(" ").concat(member.getLastName()));
      return member;

    }
  }
