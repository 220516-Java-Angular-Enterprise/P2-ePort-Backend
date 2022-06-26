package com.revature.ePort.scp;


import com.doomedcat17.scpier.data.content.ContentNode;
import com.doomedcat17.scpier.data.content.ContentNodeType;
import com.doomedcat17.scpier.data.content.ParagraphNode;
import com.doomedcat17.scpier.data.scp.ScpWikiData;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.tag.Tag;
import com.revature.ePort.tag.TagService;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SCPService {

    @Inject
    private final SCPRepository scpRepository;
    private final SCPierWrapper scPierWrapper;
    private final TagService tagService;


    @Inject
    @Autowired
    public SCPService(SCPRepository scpRepository, SCPierWrapper scPierWrapper, TagService tagService) {
        this.scpRepository = scpRepository;
        this.scPierWrapper = scPierWrapper;
        this.tagService = tagService;
    }


    //todo validation check for duplicate scp name
    public String createSCP(String name){
        try {
            ScpWikiData scpData = scPierWrapper.getScpWikiData(name);
            List<ContentNode<?>> content = scpData.getContent();
            SCP scp = new SCP();
            scp.setId(UUID.randomUUID().toString());
            scp.setName(scpData.getName());
            scp.setImg(getSCPImage(content));
            scp.setDescription(getSCPDescription(content));

            scp.getTag().addAll(scpData.getTags().stream().filter(tag -> !(tag.charAt(0) == '_')).map(tag -> {
                Tag t = tagService.checkTag(tag);
                if(t == null){t = tagService.registerTag(tag);}
                t.getScp().add(scp);
                return t;
                }).collect(Collectors.toList()));

            scpRepository.save(scp);
            return scp.getName();
        } catch (SCPierApiException e) {
            throw new RuntimeException(e);
        }
    }

    public SCP findSCPByName(String name){
        return scpRepository.findScpByName(name);
    }

    private String getSCPImage(List<ContentNode<?>> contentNodeList){
        List<String> imageList = contentNodeList.stream().filter(image-> image.getContentNodeType().equals(ContentNodeType.IMAGE)).map(image->image.getContent().toString()).collect(Collectors.toList());
        if(imageList.isEmpty()) return "ERROR NO IMAGE FOUND";
        return imageList.get(0);
    }

    private String getSCPDescription(List<ContentNode<?>> contentNodeList){
        List<ContentNode<?>> paraList = contentNodeList.stream().filter(image-> image.getContentNodeType().equals(ContentNodeType.PARAGRAPH)).collect(Collectors.toList());
        String description = "";
        exit:
        for(int i = 0; i < paraList.size(); i++){
            boolean isDescription = false; //description can be spread across multiple contents
            ParagraphNode p = (ParagraphNode) paraList.get(i);
            List<ContentNode<?>> innerContent = p.getContent().stream().collect(Collectors.toList());
            for (int j = 0; j < innerContent.size(); j++) {
                //Checks that this content node has the description and then reads all the content nodes after that into a string
                if(isDescription){
                    description += innerContent.get(j).getContent().toString();
                }else if(innerContent.get(j).getContentNodeType().equals(ContentNodeType.TEXT) && innerContent.get(j).getContent().equals("Description:")){
                    isDescription = true;
                }
            }
            if (isDescription){
                break exit;
            }
        }
        if(description.equals("")){
            description = "ERROR DESCRIPTION NOT FOUND";
        }
        return description;
    }


    //For all content
    public List<ContentNode<?>> findSCPContent(){
        try {
            ScpWikiData scpData = scPierWrapper.getScpWikiData("scp-006");
            return scpData.getContent();
        } catch (SCPierApiException e) {
            throw new RuntimeException(e);
        }
    }

    //For just images
    public List<String> findSCPImage(){
        try {
            ScpWikiData scpData = scPierWrapper.getScpWikiData("scp-006");
            List<String> imageList = scpData.getContent().stream().filter(image-> image.getContentNodeType().equals(ContentNodeType.IMAGE)).map(image->image.getContent().toString()).collect(Collectors.toList());
            //List<String> imageList = scpData.getContent().stream().filter(image-> image.getContentNodeType().equals(ContentNodeType.IMAGE)).collect(Collectors.toList()).stream().map(image -> image.getContent().toString()).collect(Collectors.toList());
            return imageList;
        } catch (SCPierApiException e) {
            throw new RuntimeException(e);
        }
    }
    public String findSCPDescription(){
        //todo make thread safe for concurrency
        try {
            ScpWikiData scpData = scPierWrapper.getScpWikiData("scp-006");
            //SCP scp = new SCP()
;
            //For the content description
            List<ContentNode<?>> paraList = scpData.getContent().stream().filter(image-> image.getContentNodeType().equals(ContentNodeType.PARAGRAPH)).collect(Collectors.toList());
            String description = "";
            exit:
            for(int i = 0; i < paraList.size(); i++){
                boolean isDescription = false; //description can be spread across multiple contents
                ParagraphNode p = (ParagraphNode) paraList.get(i);
                List<ContentNode<?>> innerContent = p.getContent().stream().collect(Collectors.toList());
                for (int j = 0; j < innerContent.size(); j++) {
                    //Checks that this content node has the description and then reads all the content nodes after that into a string
                    if(isDescription){
                        description += innerContent.get(j).getContent().toString();
                    }else if(innerContent.get(j).getContentNodeType().equals(ContentNodeType.TEXT) && innerContent.get(j).getContent().equals("Description:")){
                        isDescription = true;
                    }
                }
                if (isDescription){
                    break exit;
                }
            }
            if(description.equals("")){
                description = "ERROR DESCRIPTION NOT FOUND";
            }
            return description;
        }catch (SCPierApiException e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }
}
