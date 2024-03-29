package br.com.hodestito.CRUD2;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	Contact() {

    }

    public Contact(long id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
    
    public Contact(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
    	this.email = email;
    }

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//    public User getUser() {
//		return user;
//	}
//
 	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public String toString() {
        return "ContactList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", user='" + user.getId() + '\'' +
                '}';
    }
}