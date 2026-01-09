package pkg;

public class User {
     String UserName;
     String Password;

    // Creates a User with empty name and password.
    public User() {
        UserName = "";
        Password = "";
    }

    // Creates a User with given username and password.
    public User(String usr, String pwd) {
        UserName = usr;
        Password = pwd;
    }

    // Returns the username
    public String getUsername() {
        return UserName;
    }


    // Returns true if the stored username/password matches the parameters. Otherwise returns false.
    // Note that, even with a User with empty name and password, this is actually a valid User object (it is the default User), 
    // This function must still return false if given an empty username string.
    public boolean check(String usr, String psd) {
        if (usr == null || usr.isEmpty()) return false;
        return UserName.equals(usr) && Password.equals(psd);
    }

    // Sets a new password.
    // This function should only set the password if the current (old) password is passed in.
    // Also, a default User cannot have its password changed. 
    // Return true if password changed, return false if not.
    public boolean setPassword(String oldPass, String newPass) {
        if (UserName.isEmpty() && Password.isEmpty()) {
            return false; // default user can't change pass
        }
        if (Password.equals(oldPass)) {
            Password = newPass;
            return true;
        }
        return false;
    }
}
