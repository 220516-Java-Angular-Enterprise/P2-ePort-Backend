package com.revature.ePort.tag;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, String> {

    @Modifying
    @Query(value = "INSERT INTO tag (id, tag_name) VALUES (?1, ?2)", nativeQuery = true)
    void saveTag(String id, String name);
    Tag findTagByTagName(String tagName);
}
