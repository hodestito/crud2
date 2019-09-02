package br.com.hodestito.CRUD2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid", nullable = false)
    private String uid;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    private List<Contact> contacts = new ArrayList<>();

    User (){
    	
    }
    
    public User(String uid) {
		this.uid = uid;
	}
    
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Contact> getContactList() {
		return contacts;
	}

	public void setContactList(List<Contact> contactList) {
		this.contacts = contactList;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                '}';
    }
}