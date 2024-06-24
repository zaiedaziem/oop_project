import java.util.Scanner;
import java.io.*;

class Account {
    protected String username;
    protected String password;
    protected String name;
    protected Address address;
    protected String email;
    protected String phone;
    protected String accountType;
    
    public Account(String username, String password, String name, Address address, String email, String phone, String accountType) {
        this.username = username;
        this.password =  password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password =  password;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public static Account createAccount(String accountType, Scanner scanner) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address (lot, area, roadname, zipcode, city, state): ");
        String[] addressComponents = scanner.nextLine().split(",");
        Address address = new Address(
                addressComponents[0].trim(),
                addressComponents[1].trim(),
                addressComponents[2].trim(),
                addressComponents[3].trim(),
                addressComponents[4].trim(),
                addressComponents[5].trim()
        );
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        if ("Customer".equalsIgnoreCase(accountType)) {
            return new Customer(username, password, name, address, email, phone);
        } else if ("Seller".equalsIgnoreCase(accountType)) {
            return new Seller(username, password, name, address, email, phone);
        } else {
            return null;
        }
    }

    // Method to register a new account
    public void register() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("acc.txt", true))) {
            writer.println(username + "," + password + "," + name + "," + address.getLot() + "," + address.getArea() + "," + address.getRoadName() + "," + address.getZipcode() + "," + address.getCity() + "," + address.getState() + "," + email + "," + phone + "," + accountType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nRegistration successful!");
    }

    // Method to perform login
    public static Account login(Scanner scanner) {
        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("acc.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    Address address = new Address(data[3], data[4], data[5], data[6], data[7], data[8]);
                    if ("Customer".equalsIgnoreCase(data[11])) {
                        return new Customer(data[0], data[1], data[2], address, data[9], data[10]);
                    } else if ("Seller".equalsIgnoreCase(data[11])) {
                        return new Seller(data[0], data[1], data[2], address, data[9], data[10]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nLogin failed. Invalid credentials.");
        return null;
    }

    public void viewAccountInfo() {
        System.out.println("\n\nAccount Information:");
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address.getAddress());
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Account Type: " + accountType);
        System.out.println();

    }

    // Method to edit profile
    public void editProfile(Scanner scanner) {
        System.out.print("\nEnter new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isBlank()) {
            this.password = newPassword;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isBlank()) {
            this.name = newName;
        }

        System.out.print("Enter new address (lot, area, roadname, zipcode, city, state) (leave blank to keep current): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isBlank()) {
            String[] addressComponents = newAddress.split(",");
            this.address = new Address(
                    addressComponents[0].trim(),
                    addressComponents[1].trim(),
                    addressComponents[2].trim(),
                    addressComponents[3].trim(),
                    addressComponents[4].trim(),
                    addressComponents[5].trim()
            );
        }

        System.out.print("Enter new email (leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isBlank()) {
            this.email = newEmail;
        }

        System.out.print("Enter new phone number (leave blank to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isBlank()) {
            this.phone = newPhone;
        }

        updateAccountFile();
        System.out.println("\nProfile updated successfully!");
    }

    private void updateAccountFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("acc.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("acc_temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(this.username)) {
                    writer.println(username + "," + password + "," + name + "," + address.getLot() + "," + address.getArea() + "," + address.getRoadName() + "," + address.getZipcode() + "," + address.getCity() + "," + address.getState() + "," + email + "," + phone + "," + accountType);
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the old account file with the new one
        File oldFile = new File("acc.txt");
        oldFile.delete();
        File newFile = new File("acc_temp.txt");
        newFile.renameTo(oldFile);
    }
}