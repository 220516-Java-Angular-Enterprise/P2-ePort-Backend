package com.revature.ePort.scp;

import com.doomedcat17.scpier.ScpFoundationDataProvider;
import com.doomedcat17.scpier.data.content.ContentNode;
import com.doomedcat17.scpier.data.content.ContentNodeType;
import com.doomedcat17.scpier.data.content.ParagraphNode;
import com.doomedcat17.scpier.data.content.TextNode;
import com.doomedcat17.scpier.data.scp.SCPBranch;
import com.doomedcat17.scpier.data.scp.ScpWikiData;
import com.doomedcat17.scpier.exception.SCPierApiException;
import com.revature.ePort.user.UserRepository;
import com.revature.ePort.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SCPService {

    @Inject
    private final SCPRepository scpRepository;


    //private final ScpFoundationDataProvider scpFoundationDataProvider;

    @Inject
    @Autowired
    public SCPService(SCPRepository scpRepository) {
        this.scpRepository = scpRepository;
    }

    //For all content
    public List<ContentNode<?>> findSCPContent(){
        try {
            ScpFoundationDataProvider s = new ScpFoundationDataProvider();
            ScpWikiData scpData = s.getScpWikiData("scp-1006", SCPBranch.ENGLISH);
            return scpData.getContent();
        } catch (SCPierApiException e) {
            throw new RuntimeException(e);
        }
    }

    //For just images
    public List<String> findSCPImage(){
        try {
            ScpFoundationDataProvider s = new ScpFoundationDataProvider();
            ScpWikiData scpData = s.getScpWikiData("scp-006", SCPBranch.ENGLISH);
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
            //not thread safe
            ScpFoundationDataProvider s = new ScpFoundationDataProvider();
            ScpWikiData scpData = s.getScpWikiData("scp-1006", SCPBranch.ENGLISH);
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
