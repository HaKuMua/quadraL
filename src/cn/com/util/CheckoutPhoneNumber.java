package cn.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutPhoneNumber {
	public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "((^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$)|(^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])|(17[0,1,3,5-8]))\\d{8}$))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
