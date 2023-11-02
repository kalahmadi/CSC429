# CSC 429 - Computer Security Project GUI

Welcome to the CSC 429 Computer Security Project's main Graphical User Interface (GUI) at King Saud University. This GUI is designed to provide a platform for cryptographic operations such as encryption, decryption, hashing, digital signatures, and file handling.

## Table of Contents
- [Getting Started](#getting-started)
- [GUI Overview](#gui-overview)
- [Integration Guide](#integration-guide)
  - [Algorithm Selection](#algorithm-selection)
  - [Button Action Listeners](#button-action-listeners)
  - [Key Management](#key-management)
  - [Caesar Cipher Example](#caesar-cipher-example)
- [File Operations](#file-operations)
  - [Opening Files](#opening-files)
  - [Saving Files](#saving-files)
- [Support](#support)
- [Authors and Acknowledgement](#authors-and-acknowledgement)

## Getting Started

1. Clone or download this repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Familiarize yourself with the `SecurityGUI` class, which serves as the primary interface for your cryptographic tasks.

## GUI Overview

The GUI features areas for plaintext and ciphertext, a selection box for different algorithms, buttons for initiating cryptographic operations, and new functionality for opening and saving files.

## Integration Guide

### Algorithm Selection

Replace the placeholder in `algorithmComboBox` with the names of your cryptographic algorithms.

### Button Action Listeners

Button action listeners define the behavior upon button clicks. Each button's listener should trigger the corresponding cryptographic operation.

To integrate your cryptographic algorithm:

1. Locate the button initialization in the `SecurityGUI` class.
2. Add an action listener to the button.
3. Use the `getKeysFromFields()` method to retrieve keys when needed.
4. Implement the cryptographic operation within the listener.

"Encrypt" button example:

```java
JButton encryptButton = new JButton("Encrypt");
encryptButton.addActionListener(e -> {
    String plaintext = plaintextArea.getText();
    String key1 = getKeysFromFields()[0]; // Using the first key from the key fields
    String key2 = getKeysFromFields()[1]; // Using the second key from the key fields
    // TODO: Replace the next line with your encryption code.
    String encryptedText = encrypt(plaintext, key1); // Assume encrypt is your method
    ciphertextArea.setText(encryptedText);
});
```

Apply similar action listeners for the "Decrypt", "Hash", "HMAC", and "Digital Signature" buttons, with logic corresponding to each function.

### Key Management

To retrieve keys input by the user in the GUI:

1. Call the `getKeysFromFields()` method, which returns an array of `String` objects.
2. Access the keys as needed, for example:
`getKeysFromFields()[0]` for the first key, and `getKeysFromFields()[1]` for the second key.

Example usage within an action listener:

```java
encryptButton.addActionListener(e -> {
    String plaintext = plaintextArea.getText();
    String[] keys = getKeysFromFields(); // Retrieves all keys from the key fields
    if(keys.length > 0) {
        String encryptedText = encrypt(plaintext, keys[0]); // Use the first key
        ciphertextArea.setText(encryptedText);
    } else {
        showError("No key provided.");
    }
});
```
### Caesar Cipher Example

The Caesar Cipher is a simple shift cipher that relies on transposing all the letters in the plaintext by a certain number of positions down or up the alphabet.

Encryption example with a Caesar Cipher:

```java
JButton encryptButton = new JButton("Encrypt");
encryptButton.addActionListener(e -> {
    String plaintext = plaintextArea.getText();
    // Assuming keys[0] contains the shift value for the Caesar Cipher
    int shift = Integer.parseInt(getKeysFromFields()[0]);
    String encryptedText = caesarCipherEncrypt(plaintext, shift);
    ciphertextArea.setText(encryptedText);
});

private String caesarCipherEncrypt(String text, int shift) {
    StringBuilder result = new StringBuilder();
    for (char character : text.toCharArray()) {
        if (Character.isUpperCase(character)) {
            char ch = (char)(((int)character + shift - 65) % 26 + 65);
            result.append(ch);
        } else {
            char ch = (char)(((int)character + shift - 97) % 26 + 97);
            result.append(ch);
        }
    }
    return result.toString();
}
```

Decryption example with a Caesar Cipher:

```java
JButton decryptButton = new JButton("Decrypt");
decryptButton.addActionListener(e -> {
    String ciphertext = ciphertextArea.getText();
    // Assuming keys[0] contains the shift value for the Caesar Cipher
    int shift = Integer.parseInt(getKeysFromFields()[0]);
    String decryptedText = caesarCipherDecrypt(ciphertext, shift);
    plaintextArea.setText(decryptedText);
});

private String caesarCipherDecrypt(String text, int shift) {
    return caesarCipherEncrypt(text, 26 - shift); // Decryption is simply the reverse shift
}
```

Remember to handle any parsing errors of `shift` value gracefully by notifying the user of the error.

## File Operations

### Opening Files

Clicking the "Open File" button will trigger a file chooser to open and read any file type, placing its contents into the plaintext area.

### Saving Files

Clicking the "Save File" button will save the contents of the ciphertext area to a file, if the extension of the file is not specified then it will be saved as **.txt**.

## Support

For assistance, contact our team.

## Authors and Acknowledgement

GUI Development Team: 
- Khalid Alahmadi
- Omar Altammami
- Azzam Aljariwy