package com.amqp.constructor;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-14 15:26
 */
public class User {

    private final String userName;
    private final String password;
    private final int age;
    private final String phoneNumber;
    private final String address;

    /**
     * 静态内部类
     * 避免当entity properties过多时，每次new对象的时候都会选择不同的构造器
     */
    public static class Builder{
        private String userName;
        private String password;
        private int age = 0;
        private String phoneNumber = "";
        private String address = "";

        public Builder age(int a){
            this.age = a;
            return this;
        }

        public Builder phoneNumber(String phoneNo){
            this.phoneNumber = phoneNo;
            return this;
        }

        public Builder address(String add){
            this.address = add;
            return this;
        }

        public Builder(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public User build(){
            return new User(this);
        }
    }

    public User(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
