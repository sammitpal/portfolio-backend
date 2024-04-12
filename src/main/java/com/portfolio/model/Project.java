package com.portfolio.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    private String id;
    @NotBlank(message="Project Name cannot be blank")
    private String name;
    @NotBlank(message="Project Description cannot be blank")
    private String description;
    @Lob
    private byte[] image;
    @NotBlank(message="Technology Tags cannot be blank")
    private String technologyTags;
    private Date startDate;
    private Date endDate;
    private String demoUrl;
    private String githubUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologyTags() {
        return technologyTags;
    }

    public void setTechnologyTags(String technologyTags) {
        this.technologyTags = technologyTags;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGitubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
