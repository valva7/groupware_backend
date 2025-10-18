package org.groupware.domain.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.groupware.domain.member.repository.JpaMemberRepository;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final JpaMemberRepository jpaMemberRepository;

    public EmailService(JavaMailSender javaMailSender, JpaMemberRepository jpaMemberRepository) {
        this.javaMailSender = javaMailSender;
        this.jpaMemberRepository = jpaMemberRepository;
    }

    private void sendEmail(String receiver, String title, String signUpVerifyTemplate) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(receiver); // 메일 수신자
            mimeMessageHelper.setSubject(title); // 메일 제목
            mimeMessageHelper.setText(signUpVerifyTemplate, true); // 메일 내용
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("메일 메시지 생성 오류", e);
            throw new MailSendException("메일 메시지 생성에 실패했습니다.");
        } catch (MailAuthenticationException e) {
            log.error("메일 인증 오류", e);
            throw new MailSendException("메일 인증에 실패했습니다.");
        } catch (MailException e) {
            log.error("메일 전송 오류", e);
            throw new MailSendException("메일 전송에 실패했습니다.");
        } catch (Exception e) {
            log.error("알 수 없는 메일 전송 오류", e);
            throw new MailSendException("알 수 없는 오류가 발생했습니다.");
        }
    }
}
