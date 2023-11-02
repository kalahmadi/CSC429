# CSC 429 Computer Security Project: OTP Encryption/Decryption Tool

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [System Requirements](#system-requirements)
4. [Installation](#installation)
5. [Usage Guide](#usage-guide)
    - [Encrypting Data](#encrypting-data)
    - [Decrypting Data](#decrypting-data)
    - [File Operations](#file-operations)
6. [Development and Contribution](#development-and-contribution)
7. [Support](#support)
8. [Authors and Acknowledgement](#authors-and-acknowledgements)

## Introduction

This repository contains the CSC 429 Computer Security Project, a Java-based encryption and decryption tool using the One-Time Pad (OTP) cipher. The tool is designed to provide a highly secure mechanism for cryptographic operations on text and files, incorporating a simple yet powerful user interface for ease of use.

The OTP cipher is renowned for its theoretical unbreakability when the key is as long as the plaintext, is completely random, and is used only once. This tool adheres to those principles to ensure a high level of security in encryption and decryption processes.

## Features

- **One-Time Pad Encryption**: Offers a secure OTP encryption mechanism for text and file contents.
- **Flexible File Handling**: Encrypt or decrypt files of any kind and save the result in a specified format. If no format is specified, the default `.txt` is used.
- **Intuitive Graphical User Interface**: The application comes with a user-friendly interface that simplifies cryptographic operations.
- **Key Management System**: Securely add and manage encryption keys within the GUI.
- **Multi-platform Support**: Java-based development ensures compatibility across different operating systems.

## System Requirements

To run the CSC 429 Computer Security Project, you will need:

- Java Development Kit (JDK) 8 or later.
- A Java IDE (like IntelliJ IDEA, Eclipse, NetBeans) or command-line interface to compile and run the program.

## Installation

To install the application, follow these steps:

1. Ensure you have the required JDK installed on your system.
2. Clone this repository to your local machine:

```sh
git clone https://github.com/your-username/CSC429-SecurityProject.git
```

3. Navigate to the cloned directory.

## Usage Guide

### Starting the Application

Compile and execute the `SecurityOTP.java` file using your preferred Java IDE or via the command line. The GUI will appear, providing access to all functionalities.

### Encrypting Data

- Input or paste your plaintext in the designated 'Plaintext' area or use the 'Open File' function to load it.
- Enter the encryption key manually. Ensure that the key length is equal to the plaintext length.
- Click the 'Encrypt' button to process the plaintext. The resulting ciphertext will be displayed accordingly.

### Decrypting Data

- Load the ciphertext into the 'Ciphertext' area.
- Provide the corresponding key that was used during the encryption phase.
- Hit the 'Decrypt' button to initiate decryption. The original plaintext will be restored and shown.

### File Operations

- **Opening Files**: Click 'Open File' and choose the file for encryption or decryption.
- **Saving Files**: Use the 'Save File' button post-encryption/decryption to save the results. The application will default to `.txt` if no extension is specified.

## Development and Contribution

Contributors are welcome to propose improvements or report issues. Please follow the standard GitHub procedures to fork the repository, commit changes, and submit a pull request.

## Support

If you encounter any problems or require assistance, please raise an issue on the GitHub repository or contact the development team via the university's provided channels.

## Authors and Acknowledgement

Development Team: 
- Khalid Alahmadi
- Omar Altammami
- Azzam Aljariwy