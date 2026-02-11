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
        // Formato: CO-2100-0418-HHHH-HHHH-CCCC
        // HHHH-HHHH = 8 dígitos hash del customerId + timestamp
        // CCCC = 4 dígitos del customerId

        // Crear un hash único combinando customerId + timestamp + random
        String timestamp = String.valueOf(System.currentTimeMillis());
        String input = customerId + "-" + timestamp + "-" + secureRandom.nextInt(10000);

        // Generar hash MD5 y tomar primeros 8 dígitos
        String hash = generateShortHash(input);

        // 4 dígitos del customerId
        String customerDigits = String.format("%04d", customerId % 10000);

        String accountNumber = hash + customerDigits;

        // Formatear en grupos de 4
        String formatted = accountNumber.replaceAll("(.{4})", "$1-").replaceAll("-$", "");

        return COUNTRY_CODE + "-" + BANK_CODE + "-" + BRANCH_CODE + "-" + formatted;
    }

    private String generateShortHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convertir a hexadecimal y tomar primeros 8 caracteres
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }

            // Tomar primeros 8 caracteres y convertir a solo números
            String hashHex = hex.toString().substring(0, 8);
            return hexToDigits(hashHex);

        } catch (NoSuchAlgorithmException e) {
            // Fallback a random
            return String.format("%08d", secureRandom.nextInt(100000000));
        }
    }

    private String hexToDigits(String hex) {
        // Convertir hexadecimal a solo dígitos (0-9)
        StringBuilder digits = new StringBuilder();
        for (char c : hex.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            } else {
                // Convertir a-f a 0-5
                digits.append((c - 'a') % 6);
            }
        }

        // Asegurar 8 dígitos
        while (digits.length() < 8) {
            digits.append(secureRandom.nextInt(10));
        }

        return digits.toString().substring(0, 8);
    }
}
