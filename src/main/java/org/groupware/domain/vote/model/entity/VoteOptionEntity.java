package org.groupware.domain.vote.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vote_option")
public class VoteOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 투표 (FK: vote.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vote_id", nullable = false)
    private VoteEntity vote;

    @Column(name = "option_text", nullable = false, length = 255)
    private String optionText; // 선택지 내용

    @Column(name = "vote_count", nullable = false)
    private Long voteCount; // 선택 횟수
}
