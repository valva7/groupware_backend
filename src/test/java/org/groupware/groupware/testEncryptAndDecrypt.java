package org.groupware.groupware;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.junit.jupiter.api.Test;

public class testEncryptAndDecrypt {

    @Test
    void testEncryptAndDecrypt() {
        // 1️⃣ 암호화기 생성
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("안알려주지롱");
        encryptor.setSaltGenerator(new RandomSaltGenerator());

        // 2️⃣ 평문 입력
        String plainText = "groupware-bucket";

        // 3️⃣ 암호화
        String encrypted = encryptor.encrypt(plainText);
        System.out.println("🔒 Encrypted: ENC(" + encrypted + ")");

        // 4️⃣ 복호화
        String decrypted = encryptor.decrypt(encrypted);
        System.out.println("🔓 Decrypted: " + decrypted);
    }
}
