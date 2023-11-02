import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SecurityGUI serves as the primary interface for the CSC 429 Computer Security Project.
 * It provides a platform for plaintext input, encryption/decryption, hashing, digital signature, etc.
 *
 * Designed for easy integration with various encryption algorithms and utilities.
 *
 * @author Group Name [Members: Khalid Alahmadi, Omar Altamammi, Azzam Aljariwy]
 * @version 1.0
 */
public class SecurityOTP {

    // GUI components declarations
    private JTextArea plaintextArea;
    private JTextArea ciphertextArea;
    private JLabel algorithmDescriptionLabel;
    private JComboBox<String> algorithmComboBox;
    private JPanel keyPanel;
    private int keyFieldCount = 0;
    private static final int MAX_KEY_FIELDS = 2;
    // GUI components for file operations
    private JButton openFileButton;
    private JButton saveFileButton;

    // Constructor initializes the GUI components and builds the frame
    public SecurityOTP() {
        initLookAndFeel();
        initComponents();
        buildFrame();
    }

    // Try to set a modern LookAndFeel, defaulting to system default if an error occurs
    private void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            showError("Failed to set Look and Feel. Defaulting to system default.");
        }
    }

    // Initialize the primary components of the GUI
    private void initComponents() {
        plaintextArea = new JTextArea(10, 60);
        ciphertextArea = new JTextArea(10, 60);
        
        algorithmDescriptionLabel = new JLabel("Please Enter One Key that is the same length as your plaintext. ");
        algorithmComboBox = new JComboBox<>(new String[]{" OTP "}); 
        
        keyPanel = new JPanel();
        openFileButton = new JButton("Open File");
        openFileButton.addActionListener(this::openFileAction);

        saveFileButton = new JButton("Save File");
        saveFileButton.addActionListener(this::saveFileAction);
        
        keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS));
    }

    // Set up the main frame properties and add the primary panels
    private void buildFrame() {
        JFrame frame = new JFrame("CSC 429 - Computer Security Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(createInputPanel());
        frame.add(createAlgorithmPanel());
        frame.add(createActionPanel());

        frame.pack();
        frame.setMinimumSize(new Dimension(950, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Set up the input panel containing plaintext and ciphertext fields
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Plaintext:"));
        panel.add(new JScrollPane(plaintextArea));
        panel.add(new JLabel("Ciphertext:"));
        panel.add(new JScrollPane(ciphertextArea));
        return panel;
    }

    // Set up the algorithm panel containing algorithm selection and key input fields
    private JPanel createAlgorithmPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        
        // Upper panel for algorithm description and add key button
        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        upperPanel.add(algorithmDescriptionLabel);
        upperPanel.add(algorithmComboBox);

        JButton addKeyButton = new JButton("+");
        addKeyButton.addActionListener(e -> addKeyField());
        upperPanel.add(addKeyButton);

        // Adding the upper panel to the main panel with some space
        panel.add(upperPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); 
        
        // Lower panel for key fields
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.Y_AXIS)); 
        panel.add(keyPanel);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    
    
    // Utility method for OTP encryption
    private String encryptWithOTP(String plaintext, String key) {
        byte[] plaintextBytes = plaintext.getBytes();
        byte[] keyBytes = key.getBytes();
        byte[] cipherBytes = new byte[plaintextBytes.length];

        if (keyBytes.length < plaintextBytes.length) {
            showError("Key must be at least as long as plaintext for OTP encryption!");
            return "";
        }

        for (int i = 0; i < plaintextBytes.length; i++) {
            cipherBytes[i] = (byte) (plaintextBytes[i] ^ keyBytes[i]);
        }
        return new String(cipherBytes);
    }

    // Utility method for OTP decryption
    private String decryptWithOTP(String ciphertext, String key) {
        return encryptWithOTP(ciphertext, key);
    }

    
    
    // Set up the action panel containing primary application controls
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(e -> {
            String plaintext = plaintextArea.getText();
            String[] keys = getKeysFromFields();
            if (keys.length > 0 && keys[0].length() == plaintext.length()) {
                String key = keys[0];
                String ciphertext = encryptWithOTP(plaintext, key);
                ciphertextArea.setText(ciphertext);
            } else {
                showError("Key must be provided and match the length of the plaintext for OTP encryption.");
            }
        });
        
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(e -> {
            String ciphertext = ciphertextArea.getText();
            String[] keys = getKeysFromFields();
            if (keys.length > 0 && keys[0].length() == ciphertext.length()) {
                String key = keys[0];
                String plaintext = decryptWithOTP(ciphertext, key);
                plaintextArea.setText(plaintext);
            } else {
                showError("Key must be provided and match the length of the ciphertext for OTP decryption.");
            }
        });
        
        JButton hashButton = new JButton("Hash");
        // TODO: Replace placeholder action listener with actual hashing function
        hashButton.addActionListener(e -> ciphertextArea.setText("Hashed text would be here.")); // Placeholder function

        JButton hmacButton = new JButton("HMAC");
        // TODO: Replace placeholder action listener with actual HMAC function
        hmacButton.addActionListener(e -> ciphertextArea.setText("HMAC result would be here.")); // Placeholder function

        JButton signatureButton = new JButton("Digital Signature");
        // TODO: Replace placeholder action listener with actual digital signature function
        signatureButton.addActionListener(e -> ciphertextArea.setText("Digital signature would be here.")); // Placeholder function

        panel.add(encryptButton);
        panel.add(decryptButton);
        panel.add(hashButton);
        panel.add(hmacButton);
        panel.add(signatureButton);

        panel.add(openFileButton);
        panel.add(saveFileButton);

        return panel;
    }

    // Adds a new key input field to the keyPanel
    private void addKeyField() {
        if (keyFieldCount < MAX_KEY_FIELDS) {
            JTextField newKeyField = new JTextField();
            newKeyField.setMaximumSize(new Dimension(Integer.MAX_VALUE, newKeyField.getPreferredSize().height));
            newKeyField.setAlignmentX(Component.CENTER_ALIGNMENT);
            keyPanel.add(newKeyField);
            keyPanel.revalidate();
            keyPanel.repaint();
            keyFieldCount++; 
        } else {
            showError("Maximum number of key fields reached.");
        }
    }

    // Action method for opening files
    private void openFileAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
                plaintextArea.setText(content);
            } catch (Exception ex) {
                showError("Error reading file: " + ex.getMessage());
            }
        }
    }

    // Action method for saving files
    private void saveFileAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().contains(".")) {
                fileToSave = new File(fileToSave.toString() + ".txt");
            }
            try (FileWriter fileWriter = new FileWriter(fileToSave)) {
                fileWriter.write(ciphertextArea.getText());
            } catch (IOException ex) {
                showError("Error saving file: " + ex.getMessage());
            }
        }
    }
    
    // Retrieve keys from text fields as an array of Strings
    private String[] getKeysFromFields() {
        java.util.List<String> keysList = new java.util.ArrayList<>();
        for (Component comp : keyPanel.getComponents()) {
            if (comp instanceof JTextField) {
                keysList.add(((JTextField) comp).getText());
            }
        }
        String[] keys = new String[keysList.size()];
        keys = keysList.toArray(keys);
        return keys;
    }

    // Utility function to display error messages
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Main method to run the GUI application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SecurityOTP::new);
    }
}
