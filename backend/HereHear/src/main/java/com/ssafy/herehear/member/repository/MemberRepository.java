package com.ssafy.herehear.member.repository;

import com.ssafy.herehear.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndProvider(String email, String provider);

    @Query("select m from Member m where m.memberId = :memberId and m.removeDate = null")
    Optional<Member> findByMemberIdAndNotRemoved(@Param("memberId") Long memberId);

}
