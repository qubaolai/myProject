package com.qubaolai.common.utils;

import com.qubaolai.vo.ResultVo;
import org.apache.commons.lang.StringUtils;

/**
 * @author qubaolai
 * @date 2019/11/10 12:03
 * @ClassName: PasswordCheckUtil.java
 * @Description: 密码校验工具类
 */
public class PasswordCheckUtil {

    private static String[] KEYBOARD_SLOPE_ARR = {
            "!qaz", "1qaz", "@wsx","2wsx", "#edc", "3edc", "$rfv", "4rfv", "%tgb", "5tgb",
            "^yhn", "6yhn", "&ujm", "7ujm", "*ik,", "8ik,", "(ol.", "9ol.", ")p;/", "0p;/",
            "+[;.", "=[;.",  "_pl,", "-pl,", ")okm", "0okm", "(ijn", "9ijn", "*uhb", "8uhb",
            "&ygv", "7ygv", "^tfc", "6tfc", "%rdx","5rdx", "$esz","4esz"
    };
    private static String[] KEYBOARD_HORIZONTAL_ARR = {
            "01234567890-=",
            "!@#$%^&*()_+",
            "qwertyuiop[]",
            "QWERTYUIOP{}",
            "asdfghjkl;'",
            "ASDFGHJKL:",
            "zxcvbnm,./",
            "ZXCVBNM<>?",
    };
    private static String SPECIAL_CHAR = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    /**
     * @brief   检测密码中字符长度
     * @param[in] password            密码字符串
     * @return  符合长度要求 返回true
     */
    public static boolean checkPasswordLength(String password, String minNum, String maxNum) {
        boolean flag =false;
        if (StringUtils.isBlank(maxNum))  {
            minNum = StringUtils.isBlank(minNum) == true ? "0":minNum;
            if (password.length() >= Integer.parseInt(minNum)) {
                flag = true;
            }
        } else {
            minNum = StringUtils.isBlank(minNum) == true ? "0":minNum;
            if (password.length() >= Integer.parseInt(minNum) &&
                    password.length() <= Integer.parseInt(maxNum)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 验证密码强度
     * @param password
     * @return level 密码强度等级
     */
    public static String checkComprehensive(String password){
        String level = "";
        //是否包含数字
        boolean num = checkContainDigit(password);
        //是否包含大写字母
        boolean upperCase = checkContainUpperCase(password);
        //是否包含小写字母
        boolean lowerCase = checkContainLowerCase(password);
        //是否包含特殊字符
        boolean specialChar = checkContainSpecialChar(password);
        //强调低
        if(num == true || upperCase == true || lowerCase == true || specialChar == true){
            level = "low";
        }
        //强度中
        if((num == true && upperCase == true) || (num == true && lowerCase == true)){
            level = "medium";
        }
        if(num == true && specialChar == true){
            level = "medium";
        }
        if((specialChar == true && upperCase == true) || (specialChar == true && lowerCase == true)){
            level = "medium";
        }
        if(num == true && upperCase == true && lowerCase == true && specialChar ==true){
            level = "higher";
        }
        return level;
    }
    /**
     * @brief   检测密码中是否包含数字
     * @param[in] password            密码字符串
     * @return  包含数字 返回true
     */
    public static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int num_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                num_count++;
            }
        }
        if (num_count >= 1){
            flag = true;
        }
        return flag;
    }

    /**
     * @brief   检测密码中是否包含字母（不区分大小写）
     * @param[in] password            密码字符串
     * @return  包含字母 返回true
     */
    public static boolean checkContainCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @brief   检测密码中是否包含小写字母
     * @param[in] password            密码字符串
     * @return  包含小写字母 返回true
     */
    public static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @brief   检测密码中是否包含大写字母
     * @param[in] password            密码字符串
     * @return  包含大写字母 返回true
     */
    public static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @brief   检测密码中是否包含特殊符号
     * @param[in] password            密码字符串
     * @return  包含特殊符号 返回true
     */
    public static boolean checkContainSpecialChar(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int special_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                special_count++;
            }
        }

        if (special_count >= 1){
            flag = true;
        }
        return flag;
    }


    /**
     * @brief   键盘规则匹配器 横向连续检测
     * @param[in] password            密码字符串
     * @return  含有横向连续字符串 返回true
     */
    public static boolean checkLateralKeyboardSite(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        //将所有输入字符转为小写
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘横向规则检测
         */
        boolean flag = false;
        int arrLen = KEYBOARD_HORIZONTAL_ARR.length;
        int limit_num = repetitions ;

        for(int i=0; i+limit_num<=n; i++) {
            String str = t_password.substring(i, i+limit_num);
            String distinguishStr = password.substring(i, i+limit_num);

            for(int j=0; j<arrLen; j++) {
                String configStr = KEYBOARD_HORIZONTAL_ARR[j];
                String revOrderStr = new StringBuffer(KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                //检测包含字母(区分大小写)
                if (isLower) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                    if((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                }else {
                    if(configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if(revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }




    /**
     *
     * @Title: checkKeyboardSlantSite
     * @Description: 物理键盘，斜向连接校验， 如1qaz,4rfv, !qaz,@WDC,zaq1 返回true
     * @param password	字符串
     * @param repetitions	重复次数
     * @param isLower		是否区分大小写 true:区分大小写， false:不区分大小写
     * @return boolean    如1qaz,4rfv, !qaz,@WDC,zaq1 返回true
     * @throws
     */
    public static boolean checkKeyboardSlantSite(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘斜线方向规则检测
         */
        boolean flag = false;
        int arrLen = KEYBOARD_SLOPE_ARR.length;
        int limit_num = repetitions;

        for(int i=0; i+limit_num<=n; i++) {
            String str = t_password.substring(i, i+limit_num);
            String distinguishStr = password.substring(i, i+limit_num);
            for(int j=0; j<arrLen; j++) {
                String configStr = KEYBOARD_SLOPE_ARR[j];
                String revOrderStr = new StringBuffer(KEYBOARD_SLOPE_ARR[j]).reverse().toString();
                //检测包含字母(区分大小写)
                if (isLower) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = KEYBOARD_SLOPE_ARR[j].toUpperCase();
                    if((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                }else {
                    if(configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if(revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @Title: checkSequentialChars
     * @Description: 评估a-z,z-a这样的连续字符,
     * @param password	字符串
     * @param repetitions	连续个数
     * @param isLower		是否区分大小写 true:区分大小写， false:不区分大小写
     * @return boolean    含有a-z,z-a连续字符串 返回true
     * @throws
     */
    public static boolean checkSequentialChars(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        boolean flag = false;
        int limit_num = repetitions;
        int normal_count = 0;
        int reversed_count = 0;
        //检测包含字母(区分大小写)
        if (!isLower) {
            t_password = t_password.toLowerCase();
        }
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();

        for (int i=0; i+limit_num<=n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j=0; j<limit_num-1; j++) {
                if (pwdCharArr[i+j+1]-pwdCharArr[i+j]==1) {
                    normal_count++;
                    if(normal_count == limit_num -1){
                        return true;
                    }
                }

                if (pwdCharArr[i+j]-pwdCharArr[i+j+1]==1) {
                    reversed_count++;
                    if(reversed_count == limit_num -1){
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     *
     * @Title: checkSequentialSameChars
     * @Description: 验证键盘上是否存在多个连续重复的字符， 如！！！！, qqqq, 1111, ====, AAAA返回true
     * @param password	字符串
     * @param repetitions	重复次数
     * @return    参数
     * @return boolean    返回类型
     * @throws
     */
    public static boolean checkSequentialSameChars(String password, int repetitions) {
        String t_password = new String(password);
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();
        boolean flag = false;
        int limit_num = repetitions;
        int count = 0;
        for (int i=0; i+limit_num<=n; i++) {
            count=0;
            for (int j=0; j<limit_num-1; j++) {
                if(pwdCharArr[i+j] == pwdCharArr[i+j+1]) {
                    count++;
                    if (count == limit_num -1){
                        return true;
                    }
                }
            }
        }
        return flag;
    }
}
