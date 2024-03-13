import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FTLauncher {

    private static final String MINECRAFT_LAUNCH_COMMAND = "java -jar MinecraftLauncher.jar"; // Example launch command
    private static final String ASSETS_DIRECTORY = "assets"; // Directory to store downloaded assets

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FTLauncher::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flames Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Flames Client");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton launchButton = new JButton("Launch Game");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });
        panel.add(launchButton, BorderLayout.CENTER);

        JCheckBox offlineModeCheckbox = new JCheckBox("Offline Mode");
        panel.add(offlineModeCheckbox, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void launchGame() {
        File minecraftLauncher = new File("MinecraftLauncher.jar"); // Assuming Minecraft launcher JAR file is present
        if (minecraftLauncher.exists()) {
            try {
                // Launch Minecraft using the specified command
                String launchCommand = MINECRAFT_LAUNCH_COMMAND;
                if (isOfflineModeEnabled()) {
                    launchCommand += " --offline"; // Custom argument to indicate offline mode
                }
                Runtime.getRuntime().exec(launchCommand);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error launching Minecraft: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Minecraft launcher not found, provide a download link
            int choice = JOptionPane.showConfirmDialog(null, "Minecraft is not installed. Do you want to download it?",
                    "Minecraft Not Found", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Open browser to Minecraft homepage for download
                try {
                    Desktop.getDesktop().browse(new URI("https://www.minecraft.net/"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error opening browser: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static boolean isOfflineModeEnabled() {
        // Implement logic to check if offline mode is enabled (e.g., based on checkbox state)
        return false; // Return true if offline mode is enabled, false otherwise
    }
}
