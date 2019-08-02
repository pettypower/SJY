package com.cqkj.snail.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AttachProperty {
    
    @Value("${attach.image-path}")
    private String imagePath;

    @Value("${attach.voice-path}")
    private String voicePath;

    @Value("${attach.video-path}")
    private String videoPath;
    
    @Value("${attach.other-path}")
    private String otherFilePath;

    @Value("${attach.url}")
    private String attachUrl;
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getOtherFilePath() {
        return otherFilePath;
    }

    public void setOtherFilePath(String otherFilePath) {
        this.otherFilePath = otherFilePath;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }
    
}