package game.data;

import game.play.Player;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegisterOnFile {

    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    public static void openToRead(String fileName) {
        try {
            FileInputStream fileinputstream = new FileInputStream(fileName);
            input = new ObjectInputStream(fileinputstream);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void openToWrite(String fileName) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(fileName);
            output = new ObjectOutputStream(fileoutputstream);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void closeAfterRead() {
        try {
            if (input != null) {
                input.close();
                input = null;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void closeAfterWrite() {
        try {
            if (output != null) {
                output.close();
                output = null;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void writeObjectOnFile(Player player) {
        try {
            if (output != null) {
                output.writeObject(player);
                output.flush();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static Player readObjectFromFile() {
        Player player;
        try {
            if (input != null) {
                player = (Player) input.readObject();
                return player;
            }
        } catch (EOFException ex) {
//            System.err.println("EOF");
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        return null;
    }

}
