package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
//@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    // Note: declare logger as below
    // OR
    // simply use @Slf4j of lombok on the class JournalEntryService
    // and use it like log.info, log.error, etc.
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try{
            logger.error("Demo purpose log");
            User user = userService.findByUsername(username);
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);
        } catch (Exception e){
            logger.error("Error occured for {} : ", journalEntry.getTitle(), e.getMessage());
            throw new RuntimeException("An error occured while saving the entry. " + e.getMessage());
        }
    }

    public void updateEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId entryId) {
        return journalEntryRepository.findById(entryId);
    }

    @Transactional
    public boolean deleteById(ObjectId entryId, String username) {
        boolean removed = false;
        try{
        User user = userService.findByUsername(username);
        removed = user.getJournalEntries().removeIf(item -> item.getId().equals(entryId));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(entryId);
            }
        return removed;
        } catch (Exception e){
            System.out.println("Error occured : " + e.getMessage());
            throw new RuntimeException("An error occured while deleting the entry. " + e.getMessage());

        }
    }

}
