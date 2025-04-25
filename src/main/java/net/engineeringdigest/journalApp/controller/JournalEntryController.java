package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<ObjectId, JournalEntry> journalMap = new HashMap<>();

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalMap.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return new ArrayList<JournalEntry>(journalMap.values());
    }

    @GetMapping("/id/{entryId}")
    public JournalEntry getEntryById(@PathVariable Long entryId){
        return journalMap.get(entryId);
    }

    @DeleteMapping("/id/{entryId}")
    public JournalEntry deleteEntryById(@PathVariable Long entryId){
        return journalMap.remove(entryId);
    }

    @PutMapping("/id/{entryId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId entryId, @RequestBody JournalEntry myEntry){
        return journalMap.put(entryId, myEntry);
    }
}
