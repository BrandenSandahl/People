/**
 * Created by branden on 2/15/16 at 12:06.
 */
public class Person implements Comparable{

    private String firstName, lastName, email, country, ip;
    private int id;


    //constructors


    public Person() {
    }

    public Person(String firstName, String lastName, String email, String country, String ip, int id) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setCountry(country);
        setIp(ip);
        setId(id);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //override compareTo
    //this is strange syntax
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        if (!lastName.equalsIgnoreCase(p.lastName)) {
            return lastName.compareTo(p.lastName);
        } else {
            return firstName.compareTo(p.firstName);
        }
    }


    //override tostring
    @Override
    public String toString() {

        return String.format("%s %s at %s is in %s", firstName, lastName, email, country);
    }
}




