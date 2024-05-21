package org.licretey;

public class Test01 {
    public static void main(String[] args) {
        String str = "";
        String str1=" 　 y   ,,,,  　 ";
        String str2=" 　 y 　     ";
        String str3="1 　 y 　 2";
        String str5 = trimBothEndsChars(str1, " ,　 ");
        System.out.println("1"+str5+"2");
    }

    public static String trimBothEndsChars(String srcStr, String splitter) {
        String regex = "^[" + splitter + "]*|[" + splitter + "]*$";
        System.out.println(regex);
        return srcStr.replaceAll(regex, "");
    }
}
