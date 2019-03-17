package com.company.Mediator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TextChat chat = new TextChat();

        User admin = new Admin(chat, "Ivan Ivanovich");
        User u1 = new SimpleUser(chat, "Vana");
        User u2 = new SimpleUser(chat, "Vova");
        User u3 = new SimpleUser(chat, "Sasha");
        u2.setEnable(false);

        chat.setAdmin(admin);
        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        admin.sendMessage("Hi");
    }
}

abstract class User {
    private Chat chat;
    private String name;
    private boolean isEnable = true;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    abstract void getMessage(String message);

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}

class Admin extends User {

    public Admin(Chat chat, String name) {
        super(chat, name);
    }

    public void getMessage(String message) {
        System.out.println("Admin " + getName() + " receives message '" + message + "'");
    }
}

class SimpleUser extends User {
    public SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    public void getMessage(String message) {
        System.out.println("User " + getName() + " receives message '" + message + "'");
    }
}

interface Chat {
    void sendMessage(String message, User user);
}

class TextChat implements Chat {
    private User admin;
    private List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        if (admin != null && admin instanceof Admin) {
            this.admin = admin;
        } else {
            throw new RuntimeException("Missing rights");
        }
    }

    public void addUser(User u) {
        if (admin == null) {
            throw new RuntimeException("There is no admin in the chat!");
        }

        if (u instanceof SimpleUser) {
            users.add(u);
        } else {
            throw new RuntimeException("Admin can't participate in another char!");
        }
    }

    public void sendMessage(String message, User user) {
        if (user instanceof Admin) {
            for (User u : users) {
                u.getMessage(user.getName() + ": " + message);
            }
        }
        if (user instanceof SimpleUser) {
            for (User u : users) {
                if (u != user && u.isEnable())
                    u.getMessage(user.getName() + ": " + message);
            }
            if (admin.isEnable())
                admin.getMessage(user.getName() + ": " + message);
        }
    }

}
