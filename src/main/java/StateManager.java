import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

public class StateManager {
    private static final String FILE_NAME = "game.json";

        public void saveGame(Board board, Player[] players, int turn) {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(board.getSlots());
                String playerJson = gson.toJson(players);
                String turnJson = gson.toJson(turn);
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                fileWriter.write(json + "," +"\n"+ playerJson + ",\n" + turnJson);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void loadGame(Board board, Player[] players, int turn) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
                String everything = br.readLine();
                String[] parts = everything.split(",");
                Gson gson = new Gson();
                Type slotArrayType = new TypeToken<int[][]>() {
                }.getType();
                int boardArray = gson.fromJson(parts[0], slotArrayType);
                board.setSlot((boardArray));
                Type playerArrayType = new TypeToken<Player[]>() {
                }.getType();
                players = gson.fromJson(parts[1], playerArrayType);
               turn = gson.fromJson(parts[2], int.class);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

