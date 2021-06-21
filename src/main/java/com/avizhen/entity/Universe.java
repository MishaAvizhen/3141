package com.avizhen.entity;

import com.avizhen.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "universe")
public class Universe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "lord_id")
    private Lord lord;

    public Universe() {
    }

    public Universe(String title, Status status, Lord lord) {
        this.title = title;
        this.status = status;
        this.lord = lord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
    }
}
