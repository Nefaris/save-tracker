import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TrackerWindow extends JFrame {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton uploadToCloudButton;
    private JButton downloadFromCloudButton;
    private JTextArea outputConsole;
    private JComboBox<GameProfile> comboBox1;
    private JButton reloadProfilesButton;
    private JButton openConfigButton;

    private GameProfile currentGameProfile;
    private GameProfileStorage gameProfileStorage;

    TrackerWindow() {
        super("Save tracker");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameProfileStorage = new GameProfileStorage();
        reloadProfiles();

        uploadToCloudButton.addActionListener(e -> {
            Log log = StorageManager.upload(currentGameProfile.getLocalStoragePath(), currentGameProfile.getCloudStoragePath());
            ActivityLogger.crateLog(log);
            outputConsole.append(log.toString());
        });

        downloadFromCloudButton.addActionListener(e -> {
            Log log = StorageManager.download(currentGameProfile.getLocalStoragePath(), currentGameProfile.getCloudStoragePath());
            ActivityLogger.crateLog(log);
            outputConsole.append(log.toString());
        });

        reloadProfilesButton.addActionListener(e -> {
            reloadProfiles();
        });

        comboBox1.addItemListener(e -> {
            if (comboBox1.getItemCount() > 0) {
                int selextedProfileIndex = comboBox1.getSelectedIndex();
                textField1.setText(gameProfileStorage.getGameProfile(selextedProfileIndex).getCloudStoragePath());
                textField2.setText(gameProfileStorage.getGameProfile(selextedProfileIndex).getLocalStoragePath());
            }
        });

        openConfigButton.addActionListener(e -> {
            File configurationFile = new File(Reference.PREFERENCES_PATH);
            Desktop desktop = Desktop.getDesktop();
            if (configurationFile.exists()) {
                try {
                    desktop.open(configurationFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(rootPanel);
        setVisible(true);
    }

    private void reloadProfiles() {
        Log log;

        try {
            gameProfileStorage.loadStorage();
            comboBox1.removeAllItems();
            if (gameProfileStorage.getAlleGameProfiles() != null) {
                for (GameProfile gameProfile : gameProfileStorage.getAlleGameProfiles()) {
                    comboBox1.addItem(gameProfile);
                }
                currentGameProfile = gameProfileStorage.getGameProfile(0);
                textField1.setText(gameProfileStorage.getGameProfile(0).getCloudStoragePath());
                textField2.setText(gameProfileStorage.getGameProfile(0).getLocalStoragePath());
                log = new Log(LogType.INFO, "Reloaded succesfully");
            } else {
                log = new Log(LogType.INFO, "There is no configuration in pref.json");
            }
        } catch (MalformedJsonException | JsonSyntaxException e) {
            log = new Log(LogType.ERROR, "Json syntax error, check your preferences file");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log = new Log(LogType.ERROR, "Unable to find pref.json file");
            e.printStackTrace();
        } catch (IOException e) {
            log = new Log(LogType.ERROR, "Unable to read pref.json file");
            e.printStackTrace();
        }

        ActivityLogger.crateLog(log);
        outputConsole.append(log.toString());
    }
}
