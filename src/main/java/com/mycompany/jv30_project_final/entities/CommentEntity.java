/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author shuichi0906
 */
@Entity
@Table(name = "comment")
public class CommentEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 1000)
    private String comment;
    
    @Column(name = "comment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date commentTime;
    
    @Column(length = 100)
    private String status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
    
    public CommentEntity() {
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
