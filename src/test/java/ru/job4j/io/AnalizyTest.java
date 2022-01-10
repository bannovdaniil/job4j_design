package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAll200ThenNull() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("200 10:58:01");
            out.println("200 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is(""));
    }

    @Test
    public void whenFirst400Then() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:56:01");
            out.println("200 10:57:01");
            out.println("200 10:58:01");
            out.println("200 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:56:01;10:57:01;"));
    }

    @Test
    public void whenFirst500Then() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:56:01");
            out.println("200 10:57:01");
            out.println("200 10:58:01");
            out.println("200 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:56:01;10:57:01;"));
    }

    @Test
    public void whenFirst400and500Then() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:56:01");
            out.println("500 10:57:01");
            out.println("200 10:58:01");
            out.println("200 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:56:01;10:58:01;"));
    }

    @Test
    public void when400and500InCenterThen() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:58:01;11:01:02;"));
    }

    @Test
    public void when400and500AtlastThen() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("200 10:58:01");
            out.println("200 10:59:01");
            out.println("400 11:01:02");
            out.println("500 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("11:01:02;;"));
    }

    @Test
    public void when400and500TwoBlockslastThen() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("400 10:57:01");
            out.println("200 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:58:01;10:59:01;11:02:02;"));
    }

    @Test
    public void when500and400TwoBlockslastThen() throws IOException {
        File source = folder.newFile("log200.txt");
        File target = folder.newFile("test.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("200 10:58:01");
            out.println("400 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy anal = new Analizy();
        anal.unavailable(source.toString(), target.toString());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:58:01;10:59:01;11:02:02;"));
    }

}