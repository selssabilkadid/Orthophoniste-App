package Classes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static Map<String, UserAccount> accounts = new HashMap<>();
    private static final String FILE_PATH = "accounts.ser";
    private static UserAccount currentuser;

    static {
        loadAccounts();
        //addShutdownHook();
    }

    public static UserAccount getCurrentuser() {
        return currentuser;
    }

    public static void setcurrentuser(UserAccount currentuser) {
        AccountManager.currentuser = currentuser;
    }

    public static void addAccount(UserAccount account) {
        accounts.put(account.getEmail(), account);
        saveAccounts();
    }

    public static UserAccount getAccount(String email) {
        return accounts.get(email);
    }

    private static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            accounts = (HashMap<String, UserAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when saving accounts for the first time
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currentuser != null) {
                accounts.put(currentuser.getEmail(), currentuser);
                saveAccounts();
            }
        }));
    }
}
