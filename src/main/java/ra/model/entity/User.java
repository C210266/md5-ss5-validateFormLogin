package ra.model.entity;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Component
@Table(name = "Account")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;

    public User(Long id, String firstName, String lastName, int age, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(user.firstName.trim().equals("")){
           errors.rejectValue("firstName","invalid.empty");
        } else if (!user.firstName.matches("(^[a-zA-Z0-9._#?!@$%^&*-]{5,45}$)")) {
            errors.rejectValue("firstName", "invalid.name");
        } else if (user.lastName.trim().equals("")){
            errors.rejectValue("lastName", "invalid.empty");
        }  else if (!user.lastName.matches("(^[a-zA-Z0-9._#?!@$%^&*-]{5,45}$)")) {
            errors.rejectValue("lastName", "invalid.name");
        } else if (!user.phoneNumber.matches("(^0\\d{9}$)")){
            errors.rejectValue("phoneNumber", "invalid.tel");
        } else if (!user.email.matches("(^[A-Za-z0-9]+[A-Za-z0-9._%+-]*@[a-z]+(\\.[a-z]+)$)")){
            errors.rejectValue("email", "invalid.email");
        } else if (user.age<18){
            errors.rejectValue("age", "invalid.age");
        }
    }
}
