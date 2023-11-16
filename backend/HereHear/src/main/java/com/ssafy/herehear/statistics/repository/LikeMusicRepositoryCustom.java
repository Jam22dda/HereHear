package com.ssafy.herehear.statistics.repository;

public interface LikeMusicRepositoryCustom {
    Integer findLikeCountByMemberId(long memberId);
}
