package App;

import model.User;

public class SessionManager {
    private static User currentUser;
    private static String lastAttemptedPage;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
        lastAttemptedPage = null;
    }

    public static void setLastAttemptedPage(String page) {
        lastAttemptedPage = page;
        System.out.println("Set last attempted page: " + page);
    }

    public static String getLastAttemptedPage() {
        System.out.println("Getting last attempted page: " + lastAttemptedPage);
        return lastAttemptedPage;
    }
}
