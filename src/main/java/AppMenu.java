import javax.swing.*;

public class AppMenu extends JMenuBar {
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu profileMenu = new JMenu("Options");
    private final JMenu aboutMenu = new JMenu("About");

    private final JMenuItem profileMenuI1 = new JMenuItem("New profile");
    private final JMenuItem profileMenuI2 = new JMenuItem("Remove current profile");
    private final JMenuItem profileMenuI3 = new JMenuItem("Open profiles file");

    public AppMenu() {
        profileMenu.add(profileMenuI1);
        profileMenu.add(profileMenuI2);
        profileMenu.add(profileMenuI3);
        this.add(fileMenu);
        this.add(profileMenu);
        this.add(aboutMenu);
    }

}
