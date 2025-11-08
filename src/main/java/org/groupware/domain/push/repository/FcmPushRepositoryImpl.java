package org.groupware.domain.push.repository;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.push.entity.FcmTokenEntity;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FcmPushRepositoryImpl implements FcmPushRepository {

    private final JpaFcmPushRepository jpaFcmPushRepository;

    public FcmPushRepositoryImpl(JpaFcmPushRepository jpaFcmPushRepository) {
        this.jpaFcmPushRepository = jpaFcmPushRepository;
    }


    public void firebaseTokenSave(Member member, String fcmToken) {
        Optional<FcmTokenEntity> memberTokenInfo = jpaFcmPushRepository.findById(member.getId());

        /*
        * 유저의 토큰 정보가 없을 경우 토큰 정보 생성
        * 유저의 토큰 정보가 있을 경우 해당 토큰 정보 업데이트
        */
        FcmTokenEntity fcmTokenEntity = null;
        fcmTokenEntity = memberTokenInfo.map(tokenEntity ->
                new FcmTokenEntity(member.getId()
                                , fcmToken
                                , tokenEntity.getApprovalYn()
                                , tokenEntity.getProjectYn()
                                , tokenEntity.getPostYn()
                                , tokenEntity.getVoteYn())
                ).orElseGet(() -> new FcmTokenEntity(member.getId(), fcmToken));

        jpaFcmPushRepository.save(fcmTokenEntity);
    }

    @Override
    public String findReceiverToken(Member receiver) {
        Optional<FcmTokenEntity> tokenEntity = jpaFcmPushRepository.findById(receiver.getId());
        if (tokenEntity.isEmpty()) {
            return "";
        } else {
            return tokenEntity.get().getToken();
        }
    }

}
