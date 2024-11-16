import java.util.Scanner;

public class CipherProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Cipher Encryption-Decryption Program");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. Vigenère Cipher");
            System.out.println("3. Transposition Cipher");
            System.out.println("4. Rail Fence Cipher");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    caesarCipher(scanner);
                    break;
                case 2:
                    vigenereCipher(scanner);
                    break;
                case 3:
                    transpositionCipher(scanner);
                    break;
                case 4:
                    railFenceCipher(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void caesarCipher(Scanner scanner) {
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        System.out.print("Enter shift (integer): ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            encrypted.append(c);
        }
        System.out.println("Encrypted Text (Caesar): " + encrypted);
    }

    private static void vigenereCipher(Scanner scanner) {
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        System.out.print("Enter key: ");
        String key = scanner.nextLine().toUpperCase();
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + (key.charAt(j) - 'A')) % 26 + base);
                j = ++j % key.length();
            }
            encrypted.append(c);
        }
        System.out.println("Encrypted Text (Vigenère): " + encrypted);
    }

    private static void transpositionCipher(Scanner scanner) {
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < text.length(); i += 2) {
            if (i + 1 < text.length()) {
                encrypted.append(text.charAt(i + 1));
            }
            encrypted.append(text.charAt(i));
        }
        System.out.println("Encrypted Text (Transposition): " + encrypted);
    }

    private static void railFenceCipher(Scanner scanner) {
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        System.out.print("Enter number of rails: ");
        int rails = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) rail[i] = new StringBuilder();

        boolean down = true;
        int row = 0;
        for (char c : text.toCharArray()) {
            rail[row].append(c);
            if (row == 0) down = true;
            else if (row == rails - 1) down = false;
            row += down ? 1 : -1;
        }

        StringBuilder encrypted = new StringBuilder();
        for (StringBuilder sb : rail) {
            encrypted.append(sb);
        }
        System.out.println("Encrypted Text (Rail Fence): " + encrypted);
    }
}
