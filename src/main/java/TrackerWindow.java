import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
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

    private File cloudStorage;
    private File localStorage;

    TrackerWindow() {
        super("Save tracker");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboBox1.addItem(new GameProfile("game1", "c:/local/game1", "c:/cloud/game1"));
        comboBox1.addItem(new GameProfile("game2", "c:/local/game2", "c:/cloud/game2"));

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

        add(rootPanel);
        setVisible(true);
    }
}
