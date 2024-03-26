package com.portfolio.model;
import javax.persistence.*;

@Entity
@Table(name="resumes")
public class PdfDocument {

    @Id
    private String id;

    @Lob
    private byte[] data;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
