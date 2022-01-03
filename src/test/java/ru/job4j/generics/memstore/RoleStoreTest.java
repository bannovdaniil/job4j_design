package ru.job4j.generics.memstore;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsJunior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.add(new Role("1", "Senior"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Junior"));
    }

    @Test
    public void whenReplaceThenRoleIsHomeLess() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("1", new Role("1", "HomeLess"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("HomeLess"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Boss"));
        store.replace("10", new Role("10", "HomeLess"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Boss"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Counter"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Teacher"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Teacher"));
    }

}