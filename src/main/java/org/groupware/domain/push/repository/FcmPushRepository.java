package org.groupware.domain.push.repository;

import org.groupware.domain.member.model.Member;

public interface FcmPushRepository {

    void firebaseTokenSave(Member member, String fcmToken);

    String findReceiverToken(Member receiver);

}
