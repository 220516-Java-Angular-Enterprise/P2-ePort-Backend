package com.revature.ePort.scp;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.ePort.scpier.SCPierService;
import com.revature.ePort.tag.Tag;
import com.revature.ePort.tag.TagService;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SCPService {

    @Inject
    private final SCPRepository scpRepository;
    private final TagService tagService;
    private final SCPierService scPierService;


    @Inject
    @Autowired
    public SCPService(SCPRepository scpRepository, TagService tagService, SCPierService scPierService) {
        this.scpRepository = scpRepository;
        this.tagService = tagService;
        this.scPierService = scPierService;
    }


    //todo validation check for duplicate scp name
    public SCP createSCP(String name) {
        SCP out = scpRepository.findScpByName(name);//Can't be scp because of the lambda expression
        if (out != null) return out;
        JsonNode content = scPierService.getPlainJSON(name);
        //region Creating scp from scpier json object
        SCP scp = new SCP();
        scp.setId(UUID.randomUUID().toString());
        scp.setName(getName(content));
        scp.setDescription(getDescription(content.get("content")));
        scp.setImg(getImage(content.get("content")));

        scp.getTag().addAll(getTags(content).stream().map(tag -> {
            Tag t = tagService.checkTag(tag);
            if(t == null){t = tagService.registerTag(tag);}
            t.getScp().add(scp);
            return t;
        }).collect(Collectors.toList()));
        //endregion
        scpRepository.save(scp);
        return scp;
    }

    public SCP findSCPByName(String name){
        SCP scp = scpRepository.findScpByName(name);
        if(scp == null) throw new InvalidRequestException("SCP not in database");
        return scp;
    }

    //region Transform JsonNode into SCP object
    private String getName(JsonNode content){
        String name = content.get("name").asText();
        return name;
    }

    private List<String> getTags(JsonNode content){
        List<String> tags = new ArrayList<>();
        for (JsonNode node:content.get("tags")) {
            if(!(node.asText().charAt(0) == '_')) tags.add(node.asText());
        }
        return tags;
    }

    private String getImage(JsonNode content){
        String image = "";
        for (JsonNode node:content) {
            if(node.get("contentNodeType").asText().equalsIgnoreCase("IMAGE")){
                image = node.get("content").asText();
                break;
            }
        }
        if(image.isEmpty()) image = "ERROR NO IMAGE FOUND";
        return image;
    }

    private String getDescription(JsonNode content){
        boolean checker = false;
        String description = "";
        for (JsonNode node : content) {
            JsonNode temp = node.get("content");
            for (int i = 0; i < temp.size(); i++) {
                if(checker){
                    description += temp.get(i).get("content").asText();
                }else if(temp.get(i).get("content").asText().equalsIgnoreCase("description:")){
                    checker = true;
                }
            }
            if(checker) {
                break;
            }
        }
        return description;
    }
    //endregion

    /*public SCP createSCP(String name){
        SCP out = scpRepository.findScpByName(name);//Can't be scp because of the lambda expression
        if(out != null) return out;

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
            return scp;
        } catch (SCPierApiException e) {
            throw new RuntimeException(e);
        }
    }*/




    /*private String getSCPImage(List<ContentNode<?>> contentNodeList){
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
    }*/
}
