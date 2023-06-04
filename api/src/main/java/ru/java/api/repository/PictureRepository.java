package ru.java.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.java.api.domain.PictureEntity;

import java.util.UUID;

public interface PictureRepository extends JpaRepository<PictureEntity, UUID> {

    @Query("select p from PictureEntity p where p.messageId=:id")
    PictureEntity findFilePathByMessageId(UUID id);
}
