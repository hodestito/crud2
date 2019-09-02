package br.com.hodestito.CRUD2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
public class ContactListController {

    @Autowired
    ContactRepository contactRepository;
    
    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @GetMapping(value = "/")
    public RedirectView hello() {
        return new RedirectView("/index.html");
    }
    
    
    //GET /users 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/users")
    public ResponseEntity allUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    //GET /contacts 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/contacts")
    public ResponseEntity allContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }
    
    
    //GET /users/{uid}/contacts 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/users/{uid}/contacts")
    public ResponseEntity index(@PathVariable String uid) {
    	User user = userRepository.findByUid(uid);
        return ResponseEntity.ok(contactRepository.findAllContactsByUser(user));
    }

    //GET /users/{uid}/contact/{id}
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/users/{uid}/contacts/{id}")
    public ResponseEntity getContact(@PathVariable String uid, @PathVariable Long id) {
        Optional<Contact> foundContactList = contactRepository.findById(id);

        if(foundContactList.isPresent()){
            return ResponseEntity.ok(foundContactList.get());
        }else {
            return ResponseEntity.badRequest().body("No contact with specified id " + id + " found");
        }
    }

    //POST /users/    body: {"uid": "xxxx"} 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/users")
    public ResponseEntity addToUsers(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }    
    
    //POST /users/{uid}/contact    body: {"name": "x", "phone": "y", "email": "z"} 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/users/{uid}/contact")
    public ResponseEntity addToContactList(@PathVariable String uid, @RequestBody Contact contact) {
    	User user = userRepository.findByUid(uid);
    	contact.setUser(user);
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    
    //PUT /users/{uid}/contact/{id}     body: {"name": "x", "phone": "y", "email": "z"} 
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@PutMapping(value = "/users/{uid}/contacts/{id}")
    public ResponseEntity updateContactList(@PathVariable String uid, @PathVariable Long id, @RequestBody Contact contact) {
        Optional<Contact> optionalContactList = contactRepository.findById(id);
        if(!optionalContactList.isPresent()){
            return ResponseEntity.badRequest().body("No contact with specified id " + contact.getId() + " found");
        }

        Contact foundContactList = optionalContactList.get();
        foundContactList.setName(contact.getName());
        foundContactList.setPhone(contact.getPhone());
        foundContactList.setEmail(contact.getEmail());

        return ResponseEntity.ok(contactRepository.save(foundContactList));
    }

    //DELETE /users/{uid}
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/users/{uid}")
    public ResponseEntity removeUser(@PathVariable String uid) {
        userRepository.deleteByUid(uid);
        return ResponseEntity.noContent().build();
    }
    
    //DELETE /users/{uid}/contact/{id}
    @CrossOrigin
    @SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/users/{uid}/contacts/{id}")
    public ResponseEntity removeContactList(@PathVariable String uid, @PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}