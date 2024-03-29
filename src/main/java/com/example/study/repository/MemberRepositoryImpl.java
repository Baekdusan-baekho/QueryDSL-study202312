package com.example.study.repository;

import com.example.study.entity.Member;
import com.example.study.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.study.entity.QMember.member;

// QueryDSL용 인터페이스의 구현체는 반드시 이름의 끝이 Impl로 끝나야 자동으로 인식되어서
// 원본 인터페이스 타입(MemberRepository)의 객체로도 사용이 가능합니다. 클래스 네임 끝에 Impl을 사용해야 사용 가능
@RequiredArgsConstructor // 빈등록 주입하겠다 쿼리DSL콘피그에서 설정함
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Member> findByName(String name) {
        return queryFactory
                .selectFrom(member)
                .where(member.userName.eq(name))
                .fetch();

    }

    // WHERE절에 BooleanExpression을 리턴하는 메서드를 사용합니다.
    // nameEq, ageEq에서는 값이 없다면 null을 리턴하고, 그렇지 않을 경우 논리 표현식 결과를 리턴합니다.
    // WHERE절에서는 NULL 값인 경우 조건을 건너 뜁니다. (쿼리를 완성하지 않음)
    @Override
    public List<Member> findUser(String nameParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
                .where(nameEq(nameParam), ageEq(ageParam))
                .fetch();
    }

    private BooleanExpression ageEq(Integer ageParam){
        return ageParam != null ? member.age.eq(ageParam) : null;
    }

    private BooleanExpression nameEq(String nameParam){
//        if(nameParam != null){
//            return member.userName.eq(nameParam);
//        }
//        return null;
        return nameParam != null ? member.userName.eq(nameParam) : null;
    }


}
