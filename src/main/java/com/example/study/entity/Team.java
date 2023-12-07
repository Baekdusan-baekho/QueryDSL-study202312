package com.example.study.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString(exclude = "members") // 무한루프에 빠지지 않게 양방향 조건
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_name")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    private String name;

    @OneToMany(mappedBy = "team")
    @Builder.Default
    private List<Member> members = new ArrayList();
}
