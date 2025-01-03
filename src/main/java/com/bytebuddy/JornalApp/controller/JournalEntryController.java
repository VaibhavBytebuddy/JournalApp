//package com.bytebuddy.JornalApp.controller;
//
//import com.bytebuddy.JornalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//       private Map<Long, JournalEntry> journalEntries=new HashMap<>();
//
//       @GetMapping            //localhost:8080/journal :-----------------GET
//       public List<JournalEntry> getAll()
//       {
//           return new ArrayList<>(journalEntries.values());
//       }
//
//       @PostMapping               ////localhost:8080/journal :-----------------POST
//       public boolean createEntry(@RequestBody JournalEntry myEntry)
//       {
//               journalEntries.put(myEntry.getId(),myEntry);
//               return true;
//
//
//       }
//
//
//    @GetMapping("/id/{myId}")           //localhost:8080/journal/id/2   :-----------for GET
//    public JournalEntry getJournalEntryById(@PathVariable Long myId)
//    {
//        return journalEntries.get(myId);
//    }
//
//
//    @DeleteMapping("id/{myId}")               //localhost:8080/id/2     :-------------- for delete
//    public JournalEntry deleteJournalEntryById(@PathVariable Long myId)
//    {
//        return  journalEntries.remove(myId);
//
//    }
//
//    @PutMapping("/id/{id}")      //localhost:8080/id/2   :----------------for update
//    public JournalEntry updateJournalEntryById(@PathVariable long id, @RequestBody JournalEntry myEntry)
//    {
//       return journalEntries.put(id,myEntry);
//
//    }
//
//}
