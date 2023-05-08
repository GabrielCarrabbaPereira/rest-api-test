package org.example;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.restassured.path.json.JsonPath;

import java.util.Random;

public class Contact {
    @SerializedName("name")
    private String firstName;
    @SerializedName("last-name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("age")
    private int age;
    @SerializedName("phone")
    private Long phone;
    @SerializedName("address")
    private String address;
    @SerializedName("state")
    private String state;
    @SerializedName("city")
    private String city;

    public Contact(String firstName, String lastName, String email, int age,
                   Long phone, String address, String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.city = city;
    }

    public Contact() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.address = faker.address().streetAddress();
        this.phone = 5551998451627L;
        this.state = faker.address().state();
        this.city = faker.address().city();

        Random rand = new Random();
        this.age = rand.nextInt(100);
        this.email = this.firstName + this.lastName + this.age + "@gmail.com";
    }

    public Contact(JsonPath jsonContato) {
        jsonContato.setRootPath("data.attributes");

        this.firstName = jsonContato.getString("name");
        this.lastName = jsonContato.getString("last-name");
        this.email = jsonContato.getString("email");
        this.age = jsonContato.getInt("age");
        this.phone = jsonContato.getLong("phone");
        this.address = jsonContato.getString("adress");
        this.state = jsonContato.getString("state");
        this.city = jsonContato.getString("city");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Contact other = (Contact) obj;
        return this.firstName.equals(other.getFirstName()) &&
                this.lastName.equals(other.getLastName()) &&
                this.email.equals(other.getEmail()) &&
                this.age == other.getAge() &&
                this.phone.equals(other.getPhone()) &&
                this.address.equals(other.getAddress()) &&
                this.state.equals(other.getState()) &&
                this.city.equals(other.getCity());
    }

    public String asString() {
        Gson gson = new Gson();
        return gson.toJson(this);
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

    public int getAge() {
        return age;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setAge(int age) { this.age = age; }

    public void setPhone(Long phone) { this.phone = phone; }

    public void setAddress(String address) { this.address = address; }

    public void setState(String state) { this.state = state; }

    public void setCity(String city) { this.city = city; }
}
