package ru.job4j.generics.memstore;

public class Role extends Base {
    private final String role;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
