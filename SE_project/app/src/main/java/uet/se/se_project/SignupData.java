package uet.se.se_project;

class SignupData {
    private String userName;
    private String email;
    private String reg_No;
    private String password;

    public SignupData() {
    }

    public SignupData(String userName, String email, String reg_No, String password) {
        this.userName = userName;
        this.email = email;
        this.reg_No = reg_No;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReg_No() {
        return reg_No;
    }

    public void setReg_No(String reg_No) {
        this.reg_No = reg_No;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
