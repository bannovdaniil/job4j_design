package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bannov Daniil"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bannov Daniil"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongNameComment() {
        String path = "./data/pair_with_exception_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bannov Daniil"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongArgumentComment() {
        String path = "./data/pair_exception_name_comment.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Bannov Daniil"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}