package com.bytebuddy.JornalApp.controller;

import com.bytebuddy.JornalApp.entity.JournalEntry;
import com.bytebuddy.JornalApp.entity.User;
import com.bytebuddy.JornalApp.service.JournalEntryService;
import com.bytebuddy.JornalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;



    @GetMapping()           //localhost:8080/journal :-----------------GET
    public ResponseEntity<?> getJournalEntriesOfUser()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName=auth.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()              ////localhost:8080/journal :-----------------POST
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
       try {

           Authentication auth=SecurityContextHolder.getContext().getAuthentication();
           String userName=auth.getName();
            journalEntryService.saveEntry(myEntry,userName);
           return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
       }catch (Exception e)
       {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }



    }


    @GetMapping("/id/{myId}")           //localhost:8080/journal/id/2   :-----------for GET by id
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId)
    {

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent())
        {
              return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }

            return new ResponseEntity<>( HttpStatus.NOT_FOUND);



    }


    @DeleteMapping("id/{userName}/{myId}")             //localhost:8080/journal/id/Ram/676ced720019f8068e8c6a0f     :-------------- for delete
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName)
    {
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @PutMapping("/id/{userName}/{id}")      //localhost:8080/journal/id/Vipul/676cf721bb549f3e6d57fda7  :----------------for update
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry,@PathVariable String userName)
   {

       JournalEntry old=journalEntryService.findById(id).orElse(null);
       if(old !=null)
       {
           old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

          journalEntryService.saveEntry(old);
         return new ResponseEntity<>(old,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}
