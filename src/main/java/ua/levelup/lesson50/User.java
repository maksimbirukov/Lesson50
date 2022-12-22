package ua.levelup.lesson50;

import java.util.List;

public class User {

    private static int counter = 0;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = ++counter;
    }

    public User(String name) {
        this(name, 30);
    }

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String nameWithAge() {
        return "Name: " + this.name + ", age:" + this.age;
    }

    public String nameWithAge(String delimiter) {
        return String.join(delimiter, List.of(this.name, String.valueOf(this.age)));
    }

    public boolean isAdmin() {
        return true;
    }

    public String getType() {
        return "user";
    }

    public static String getExprValue(String arg) {
        return "Example of invocation with param: " + arg;
    }
}
