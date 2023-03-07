package contacts_1.logic;

import java.io.*;

public class SerializableUtil {

    // only serialize class can use: static class
    public static void writeOtoFile(Object obj, String fileName) {
        try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oo.writeObject(obj);
        } catch (FileNotFoundException f) {
            System.out.println("No file " + fileName + "in directory");
        } catch (IOException e) {
            System.out.println("Can't save file!");
        }
    }

    // only serialize class can use: static class
    public static Object readToObject(String fileName) {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fileName))) {
            return oi.readObject();
        } catch (FileNotFoundException f) {
            System.out.println("Cannot read file " + fileName);
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't read file!");
        }
        return null;
    }
}
