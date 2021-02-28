package com.savelchev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        return s;
    }

    public static int readInt() {
        try {
            return Integer.parseInt(readString());
        }catch (NumberFormatException e) {
            //ConsoleHelper.writeMessage("Введите другую цифру:");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
