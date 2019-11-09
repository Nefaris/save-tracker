import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileChooserUtils {
    public static File getLocation(String windowTitle, Component parent) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.setDialogTitle(windowTitle);
        jFileChooser.showOpenDialog(parent);
        return new File(jFileChooser.getSelectedFile().toString());
    }
}
