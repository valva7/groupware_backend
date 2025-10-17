package org.groupware.domain.push.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.groupware.domain.member.model.Member;
import org.groupware.domain.push.repository.FcmPushRepository;
import org.springframework.stereotype.Service;

@Service
public class FcmPushService {

    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님의 게시물을 좋아합니다!";
    private static final String MESSAGE_KEY = "message";

    private final FcmPushRepository fcmPushRepository;

    public FcmPushService(FcmPushRepository fcmPushRepository) {
        this.fcmPushRepository = fcmPushRepository;
    }

    /**
     * 좋아요 알림 전송
     * @param sender
     * @param receiver
     */
    public void sendLikeMessage(Member sender, Member receiver) {
        String receiverToken = fcmPushRepository.findReceiverToken(receiver);

        if(!receiverToken.isEmpty()) {
            Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getInfo().getMemberName(), receiver.getInfo().getMemberName()))
                .setToken(receiverToken)
                .build();
            FirebaseMessaging.getInstance().sendAsync(message);
        }
    }

}
