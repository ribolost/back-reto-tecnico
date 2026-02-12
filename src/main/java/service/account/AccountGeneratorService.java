package service.account;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class AccountGeneratorService {
    private static final String COUNTRY_CODE = "CO";
    private static final String BANK_CODE = "2100";
    private static final String BRANCH_CODE = "0418";
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateAccountNumberForCustomer(Integer customerId) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String input = customerId + "-" + timestamp + "-" + secureRandom.nextInt(10000);

        String hash = generateShortHash(input);

        String customerDigits = String.format("%04d", customerId % 10000);

        String accountNumber = hash + customerDigits;

        String formatted = accountNumber.replaceAll("(.{4})", "$1-").replaceAll("-$", "");

        return COUNTRY_CODE + "-" + BANK_CODE + "-" + BRANCH_CODE + "-" + formatted;
    }

    private String generateShortHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }

            String hashHex = hex.toString().substring(0, 8);
            return hexToDigits(hashHex);

        } catch (NoSuchAlgorithmException e) {
            return String.format("%08d", secureRandom.nextInt(100000000));
        }
    }

    private String hexToDigits(String hex) {
        StringBuilder digits = new StringBuilder();
        for (char c : hex.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            } else {
                digits.append((c - 'a') % 6);
            }
        }

        while (digits.length() < 8) {
            digits.append(secureRandom.nextInt(10));
        }

        return digits.substring(0, 8);
    }
}
