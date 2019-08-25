package br.com.hodestito.CRUD2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
public class ContactListController {

    @Autowired
    ContactListRepository contactListRepository;

    @GetMapping(value = "/")
    public RedirectView hello() {
        return new RedirectView("/index.html");
    }
    
    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/list")
    public ResponseEntity index() {
        return ResponseEntity.ok(contactListRepository.findAll());
    }

    @SuppressWarnings("rawtypes")
	@GetMapping(value = "/contact")
    public ResponseEntity getContact(@RequestParam(value="id") Long id) {
        Optional<ContactList> foundContactList = contactListRepository.findById(id);

        if(foundContactList.isPresent()){
            return ResponseEntity.ok(foundContactList.get());
        }else {
            return ResponseEntity.badRequest().body("No contact with specified id " + id + " found");
        }
    }

    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/")
    public ResponseEntity addToContactList(@RequestParam(value="name") String name, @RequestParam(value="description") String desc) {
        return ResponseEntity.ok(contactListRepository.save(new ContactList(name, desc)));
    }

    @SuppressWarnings("rawtypes")
	@PutMapping(value = "/")
    public ResponseEntity updateContactList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id, @RequestParam(value="description") String desc) {
        Optional<ContactList> optionalContactList = contactListRepository.findById(id);
        if(!optionalContactList.isPresent()){
            return ResponseEntity.badRequest().body("No contact with specified id " + id + " found");
        }

        ContactList foundContactList = optionalContactList.get();
        foundContactList.setName(name);
        foundContactList.setDescription(desc);

        return ResponseEntity.ok(contactListRepository.save(foundContactList));
    }

    @SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/")
    public ResponseEntity removeContactList(@RequestParam(value="id") Long id) {
        contactListRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}