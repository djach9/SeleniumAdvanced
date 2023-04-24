package configuration;

public class User   {
    private String socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthdate;


    private String address;
        private String zipCode;
        private String city;

        private String state;
        private String invoiceAddress;
        private String invoiceZipCode;
        private String invoiceCity;

        public String getSocialTitle() {
            return socialTitle;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public String getAddress() {
            return address;
        }

        public String getZipCode() {
            return zipCode;
        }

        public String getCity() {
            return city;
        }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

        public String getInvoiceAddress() {
            return invoiceAddress;
        }

        public String getInvoiceZipCode() {
            return invoiceZipCode;
        }

        public String getInvoiceCity() {
            return invoiceCity;
        }

public static final class UserBuilder {
    private String socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthdate;

    public UserBuilder socialTitle(String socialTitle) {
        this.socialTitle = socialTitle;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = firstName + "." + lastName + "@test.com";
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder birthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public User build() {
        if (socialTitle.isEmpty()) {
            throw new IllegalStateException("Social title cannot be empty");
        }

        if (firstName.isEmpty()) {
            throw new IllegalStateException("First name cannot be empty");
        }

        if (lastName.isEmpty()) {
            throw new IllegalStateException("Last name cannot be empty");
        }

        if (password.isEmpty()) {
            throw new IllegalStateException("Password cannot be empty");
        }

        User user = new User();
        user.socialTitle = this.socialTitle;
        user.firstName = this.firstName;
        user.lastName = this.lastName;
        user.email = this.email;
        user.password = this.password;
        user.birthdate = this.birthdate;
        return user;
    }
}
}
