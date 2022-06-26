package com.revature.ePort.tag;

import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TagService {

    @Inject
    private final TagRepository tagRepository;

    @Inject
    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public Tag registerTag(String tagName){
        String id = UUID.randomUUID().toString();
        Tag tag = new Tag();
        tag.setId(id);
        tag.setTagName(tagName);
        return tagRepository.save(tag);
    }

    public Tag checkTag(String tagName){
        return tagRepository.findTagByTagName(tagName);
    }
}
