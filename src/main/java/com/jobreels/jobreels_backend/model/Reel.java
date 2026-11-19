package com.jobreels.jobreels_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reels")
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String creatorName;
    private String creatorRole;
    private String title;
    private String category;
    private String videoUrl;
    private Long linkedJobId;
    private int likesCount = 0;
    private boolean removed = false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorRole() {
		return creatorRole;
	}
	public void setCreatorRole(String creatorRole) {
		this.creatorRole = creatorRole;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public Long getLinkedJobId() {
		return linkedJobId;
	}
	public void setLinkedJobId(Long linkedJobId) {
		this.linkedJobId = linkedJobId;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public boolean isRemoved() {
		return removed;
	}
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
    
    
}