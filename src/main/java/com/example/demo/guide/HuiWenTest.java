package com.example.demo.guide;

public class HuiWenTest {

    public static void main(String[] args) {
        String str = "abcb";
        System.out.println(longestPalindromeSubseq(str));

        /*String str = "123";
        System.out.println(str2Int(str));*/
    }

    /**
     * 返回最大回文子串
     * @param str
     * @return
     */
    public static String huiWenStr(String str){
        String result = "";
        int maxLength = 0;

        for(int i = 0; i < str.length(); i++){
            int start = i;
            int end = i + 1;
            while(start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)){
                start--;
                end++;
            }
            if(end - start + 1 > maxLength){
                maxLength = end - start + 1;
                result = str.substring(start + 1, end);
            }
            start = i - 1;
            end = i + 1;
            //有对称轴的 eg:abcba
            while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)){
                start--;
                end++;
            }
            if(end - start + 1 > maxLength){
                maxLength = end - start + 1;
                result = str.substring(start + 1, end);
            }
        }

        return result;
    }

    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i>=0; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

    public static int str2Int(String str){
        char[] chars = str.toCharArray();
        int res = 0;
        int flag = 0;
        //判断是否存在符号位
        if(chars[0] == '+'){
            flag = 1;
        }else if(chars[0] == '-'){
            flag = 2;
        }
        for (int i = flag == 0 ? 0 : 1; i < chars.length; i++) {
            if(!Character.isDigit(chars[i])){
                throw new RuntimeException("非数字");
            }
            //减去字符0，也就是减去0的ASCII码值48，数字字符减去‘0’就得到了该数字。
            int temp = chars[i] - '0';
            res = res * 10 + temp;
        }
        return flag != 2 ? res : -res;
    }

}
