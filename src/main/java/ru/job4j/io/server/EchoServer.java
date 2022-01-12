package ru.job4j.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {
    /**
     * банальностью поиска msg=Bye заниматься не стоит
     * написал полноценный парсер GET запросов
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        Map<String, String> params = getHead(str);
                        if (checkParam(params, "msg", "Bye")) {
                            server.close();
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }

    /**
     * Загоняем все параметры из строки в map
     *
     * @param str - строка из запроса
     * @return - Map(key, value) с параметрами
     */
    private static Map<String, String> getHead(String str) {
        Map<String, String> params = new HashMap<>();
        String[] part = str.split(" ");
        if (part[0].startsWith("GET") && part[2].startsWith("HTTP/")) {
            if (part[1].startsWith("/?") && part[1].contains("=")) {
                String parLine = part[1].substring(2) + "&";
                for (String param : parLine.split("&")) {
                    String[] keys = param.split("=");
                    if (keys[0] != null && keys[1] != null) {
                        params.putIfAbsent(keys[0], keys[1]);
                    }
                }
            }
        }
        return params;
    }

    /**
     * проверяет наличие key=value
     *
     * @param params - Map с параметрами из URL
     * @param key    - параметр
     * @param value  - значение
     * @return - true = найден / false = нет
     */
    private static boolean checkParam(Map<String, String> params, String key, String value) {
        return params.containsKey(key) && value.equals(params.get(key));
    }
}
