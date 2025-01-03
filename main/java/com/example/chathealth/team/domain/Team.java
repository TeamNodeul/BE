package com.example.chathealth.team.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "team") //매핑되는 엔티티의 필드명
    private List<User> users= new ArrayList<>();

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // automatically delete,create member when team is deleted, or created
    private List<Member> members= new ArrayList<>();
    @Column(nullable = false, length = 200,name ="team_name" )
    private String TeamName;
    @Getter
    @Column(nullable = false, length = 200,name ="head" )
    private Long Head;

    public Team() {

    }

    //need team make method
    public Team(String teamName , Long head){
        if(teamName ==null|| teamName.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 이름: (%s)", teamName));

        }
        this.TeamName=teamName;
        this.Head=head;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {

        return TeamName;
    }

    public User getUser() {
        return user;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Long getHead() {
        return Head;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public void setHead(Long head) {
        Head = head;
    }
}
