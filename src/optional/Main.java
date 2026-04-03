package optional;

import java.util.Optional;

public class Main {

    // OLD WAY (before Optional):
    // Method returns String or null
    static String findUsernameOld(int id) {
        if (id == 1) {
            return "Alice";
        }
        return null; // no value found
    }

    // NEW WAY (with Optional):
    // Method returns Optional<String>
    static Optional<String> findUsernameNew(int id) {
        if (id == 1) {
            return Optional.of("Alice");
        }
        return Optional.empty(); // no value found
    }

    public static void main(String[] args) {
        // ===== OLD WAY =====
        String oldUser = findUsernameOld(2);

        if (oldUser != null) {
            System.out.println("Old way user: " + oldUser);
        } else {
            System.out.println("Old way: user not found");
        }

        // ===== NEW WAY =====
        Optional<String> newUser = findUsernameNew(2);

        String result = newUser.orElse("user not found");
        System.out.println("New way user: " + result);

        // ===== ofNullable example =====
        String valueFromOldCode = findUsernameOld(2); // may be null

        Optional<String> wrapped = Optional.ofNullable(valueFromOldCode);
        System.out.println("Wrapped old result: " + wrapped.orElse("user not found"));
    }
}