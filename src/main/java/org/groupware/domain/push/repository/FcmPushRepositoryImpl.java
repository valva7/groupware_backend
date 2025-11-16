package org.groupware.domain.push.repository;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.push.entity.FcmTokenEntity;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FcmPushRepositoryImpl implements FcmPushRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    public FcmPushRepositoryImpl(JpaFcmTokenRepository jpaFcmTokenRepository) {
        this.jpaFcmTokenRepository = jpaFcmTokenRepository;
    }


    public void firebaseTokenSave(Member member, String fcmToken) {
        Optional<FcmTokenEntity> memberTokenInfo = jpaFcmTokenRepository.findById(member.getInfo().getMemberId());

        /*
        * 유저의 토큰 정보가 없을 경우 토큰 정보 생성
        * 유저의 토큰 정보가 있을 경우 해당 토큰 정보 업데이트
        */
        FcmTokenEntity fcmTokenEntity = null;
        fcmTokenEntity = memberTokenInfo.map(tokenEntity ->
                new FcmTokenEntity(member.getInfo().getMemberId()
                                , fcmToken
                                , tokenEntity.getApprovalYn()
                                , tokenEntity.getProjectYn()
                                , tokenEntity.getPostYn()
                                , tokenEntity.getVoteYn())
                ).orElseGet(() -> new FcmTokenEntity(member.getInfo().getMemberId(), fcmToken));

        jpaFcmTokenRepository.save(fcmTokenEntity);
    }

    @Override
    public String findReceiverToken(Member receiver) {
        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(receiver.getInfo().getMemberId());
        if (tokenEntity.isEmpty()) {
            return "";
        } else {
            return tokenEntity.get().getToken();
        }
    }

}
