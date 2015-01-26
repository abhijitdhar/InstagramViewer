package com.adapp.instagramviewer;

/**
 * Created by abhidhar on 1/22/15.
 */
public class InstagramPhoto {

    private String username;
    private String profilePicture;
    private int postTime;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likesCount;

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getPostTime() {
        return postTime;
    }

    public void setPostTime(int postTime) {
        this.postTime = postTime;
    }

    public String getUsername() {  return username;   }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public String toString() {
        return "username='" + username + "caption=" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageHeight=" + imageHeight +
                ", likesCount=" + likesCount +
                '}';
    }


}
