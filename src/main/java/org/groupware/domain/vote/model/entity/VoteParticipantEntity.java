package org.groupware.domain.vote.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vote_participant")
public class VoteParticipantEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 투표 (FK: vote.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vote_id", nullable = false)
    private VoteEntity vote;

    // 투표자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voter_id", nullable = false)
    private MemberEntity voter;

    // 선택한 옵션 (FK: vote_option.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private VoteOptionEntity option;

    @Column(name = "voted_at", nullable = false)
    private LocalDateTime votedAt; // 투표 일시
}
