import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TrackerWindow extends JFrame {
    private JPanel rootPanel;
    private JButton cloudStorageButton;
    private JTextField textField1;
    private JButton localStorageButton;
    private JTextField textField2;
    private JButton uploadToCloudButton;
    private JButton downloadFromCloudButton;
    private JTextArea textArea1;
    private JComboBox<GameProfile> comboBox1;
    private JButton saveProfileButton;
    private JButton loadProfileButton;

    private File cloudStorage;
    private File localStorage;
    private GameProfile[] gameProfiles;

    TrackerWindow() {
        super("Save tracker");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            gameProfiles = GameProfileManager.getAllProfiles();
            for (GameProfile gameProfile : gameProfiles) {
                comboBox1.addItem(gameProfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        cloudStorageButton.addActionListener(e -> {
            cloudStorage = FileChooserUtils.getLocation("Select cloud storage", this);
            textField1.setText(cloudStorage.getPath());
        });

        localStorageButton.addActionListener(e -> {
            localStorage = FileChooserUtils.getLocation("Select local storage", this);
            textField2.setText(localStorage.getPath());
        });

        uploadToCloudButton.addActionListener(e -> {
            try {
                FileUtils.copyDirectory(localStorage, cloudStorage);
                textArea1.append(new Date() + ": Local > Cloud\n");
            } catch (Exception ex) {
                textArea1.append(new Date() + ": Something went wrong ... Local > Cloud\n");
                ex.printStackTrace();
            }
        });

        downloadFromCloudButton.addActionListener(e -> {
            try {
                FileUtils.copyDirectory(cloudStorage, localStorage);
                textArea1.append(new Date() + ": Local < Cloud\n");
            } catch (Exception ex) {
                textArea1.append(new Date() + ": Something went wrong ... Local < Cloud\n");
                ex.printStackTrace();
            }
        });

        loadProfileButton.addActionListener(e -> {
            String localPath = gameProfiles[comboBox1.getSelectedIndex()].getLocalPath();
            String cloudPath = gameProfiles[comboBox1.getSelectedIndex()].getCloudPath();
            localStorage = new File(localPath);
            cloudStorage = new File(cloudPath);
            textField1.setText(localPath);
            textField2.setText(cloudPath);
        });

        add(rootPanel);
        setVisible(true);
    }
}
