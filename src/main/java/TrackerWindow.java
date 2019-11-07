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

    private File cloudStorage;
    private File localStorage;

    TrackerWindow() {
        super("Save tracker");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        cloudStorageButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.setDialogTitle("Select cloud storage");
            jFileChooser.showOpenDialog(this);
            File selectedFile = new File(jFileChooser.getSelectedFile().toString());
            cloudStorage = selectedFile;
            textField1.setText(selectedFile.getPath());
        });

        localStorageButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.setDialogTitle("Select local storage");
            jFileChooser.showOpenDialog(this);
            File selectedFile = new File(jFileChooser.getSelectedFile().toString());
            localStorage = selectedFile;
            textField2.setText(selectedFile.getPath());
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
