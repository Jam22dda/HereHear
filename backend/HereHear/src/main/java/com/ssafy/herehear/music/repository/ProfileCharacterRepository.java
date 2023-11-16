package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.ProfileCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("musicProfileCharacterRepository")
public interface ProfileCharacterRepository extends JpaRepository<ProfileCharacter, Long> {

}
