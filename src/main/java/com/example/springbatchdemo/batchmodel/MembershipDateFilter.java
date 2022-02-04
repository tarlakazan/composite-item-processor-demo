package com.example.springbatchdemo.batchmodel;

import com.example.springbatchdemo.domain.Member;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MembershipDateFilter implements ItemProcessor<Member, Member> {
    @Override
    public Member process(Member member){
        LocalDate date = LocalDate.parse(member.getMembershipDate());
        if(date.isBefore(LocalDate.now().minusMonths(6))){
            return member;
        }
        return null;
    }
}
