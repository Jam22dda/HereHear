package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.MemberReadList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberReadListRepository extends JpaRepository<MemberReadList, Long> {

}

