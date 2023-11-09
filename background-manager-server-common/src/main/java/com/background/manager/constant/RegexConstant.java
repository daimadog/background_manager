package com.background.manager.constant;

/**
 * @Description: 正则表达式常量
 * @Author: 杜黎明
 * @Date: 2023/02/23 09:15:54
 * @Version: 1.0.0
 */
public class RegexConstant {

    public class Regex {
        /** 运营用户名正则表达式**/
        public static final String USERNAME_REGEX="^[a-zA-Z][A-Za-z0-9_]{3,15}$";
        /** 运营用户密码正则表达式 **/
        public static  final  String PASSWORD_REGEX = "^(?=.{8})(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d).*$";
        /** 手机号码正则表达式**/
        public final static String PHONE_REGEX = "^[1][3-9]\\d{9}$";
        /** 邮箱地址正则表达式**/
        public final static String EMAIL_REGEX = "^[a-zA-Z0-9_.-]{2,16}@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    }

    public class Message {

        /** 运营用户名提示信息**/
        public static final String USERNAME_NAME="用户名长度为4-16字符，以字母开头，由字母、数字以及下划线组成";
        /** 运营用户密码提示信息 **/
        public static final String PASSWORD_NAME = "密码长度为8-32字符，由大写字母、小写字母和数字组成";
        /** 手机号码提示信息**/
        public final static String PHONE_NAME= "手机号码格式不正确";
        /** 邮箱地址提示信息**/
        public final static String EMAIL_NAME = "邮箱地址格式不正确";
    }



}
