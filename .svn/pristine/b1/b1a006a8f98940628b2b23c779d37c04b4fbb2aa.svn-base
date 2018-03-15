package fengkongweishi.util;

public class HideCharsUtil {

    public static String hideIDCard(String idCard) {
        String headCard = idCard.substring(0, 4);
        String endCard = idCard.substring(16, 18);
        return headCard + "************" + endCard;
    }

    public static String hideMobile(String mobile) {
        if (mobile.length() >= 11) {
            int length = mobile.length();
            String headMobile = mobile.substring(0, length - 8);
            String endMobile = mobile.substring(length - 4, length);
            return headMobile + "****" + endMobile;
        } else {
            return mobile;
        }

    }

    public static String hideBankCard(String bankCard) {
        String headBankCard = bankCard.substring(0, 4);
        int length = bankCard.length();
        String endBankCard = bankCard.substring(length - 4, length);
        return headBankCard + "***********" + endBankCard;
    }

}
