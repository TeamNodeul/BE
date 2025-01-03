package com.example.chathealth.history.domain;
import com.example.chathealth.history.domain.CountHistory;
import com.example.chathealth.team.domain.User;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "dailyhistory")
public class DailyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @OneToMany(mappedBy = "dailyHistory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
    private List<CountHistory> countHistory = new ArrayList<>();
    @ManyToOne //매핑
    private User user = new User();

    @Column(nullable = true, name  ="total_time" )
    private Time TotalTime = null;

    @Column(nullable = true, name  ="total_weight" )
    private Integer  TotalWeight = null;


    protected DailyHistory() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Time getTotalTime() {
        return TotalTime;
    }

    public Integer getTotalWeight() {
        return TotalWeight;
    }
}
