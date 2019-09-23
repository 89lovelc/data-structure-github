package com.lovelc;

/**
 * 暴力匹配
 */
public class ForceFor {

    public static boolean pattern(String source, String find) {
        char[] sourceChars = source.toCharArray();
        char[] findChars = find.toCharArray();

        //遍历 sourceChars
        int index = 0 ;

        //遍历 findChars
        int findIndex = 0;


        for (int i = 0; i <= sourceChars.length - find.length(); i++) {
            index = i ;
            findIndex = 0 ;
            while (findIndex < findChars.length && sourceChars[index] == findChars[findIndex]
                     ) {
                index ++;
                findIndex ++;
            }

            if (findIndex == findChars.length) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(pattern("asjdlkfjasd;jf","lkfja"));


    }



}
