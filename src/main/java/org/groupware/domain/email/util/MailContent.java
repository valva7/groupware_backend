package org.groupware.domain.email.util;

public class MailContent {

    public static String signUpComplete(String loginId) {
        return """
        <!DOCTYPE html>
        <html lang="ko">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Coev1 그룹웨어 계정 생성 완료</title>
            <style>
                /* 기본 스타일 */
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    margin: 0;
                    padding: 0;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    min-height: 100vh;
                }
                .container {
                    max-width: 600px;
                    background: #ffffff;
                    padding: 20px;
                    border-radius: 8px;
                    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                    text-align: center;
                }
                h1 {
                    color: #333;
                    font-size: 24px;
                    margin-bottom: 10px;
                }
                p {
                    color: #555;
                    font-size: 16px;
                    margin-bottom: 20px;
                }
                .code {
                    display: inline-block;
                    background: #4AFF8C;
                    color: #333;
                    font-size: 28px;
                    font-weight: bold;
                    padding: 12px 20px;
                    border-radius: 5px;
                    letter-spacing: 2px;
                    margin: 15px 0;
                }
                .footer {
                    margin-top: 20px;
                    font-size: 14px;
                    color: #999;
                }
                /* 반응형 디자인 */
                @media (max-width: 600px) {
                    .container {
                        width: 90%;
                        padding: 15px;
                    }
                    .code {
                        font-size: 24px;
                        padding: 10px 16px;
                    }
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Coev1 그룹웨어 계정 생성 완료</h1>
                <p>안녕하세요! Coev1 그룹웨어 계정 생성이 완료되었습니다. </p>
                <p>계정 정보는 아래와 같습니다.</p>
                <p>비밀번호는 생년월일입니다.</p>
                <div class="code">"""
                + loginId + """
                </div>
                <p class="footer">이 메일은 Coev1 요청에 의해 발송되었습니다.</p>
            </div>
        </body>
        </html>
        """;
    }

}
