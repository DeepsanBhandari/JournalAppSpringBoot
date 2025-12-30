package com.deepsan.spring.service;

import com.deepsan.spring.entity.JournalEntry;
import com.deepsan.spring.entity.User;
import com.deepsan.spring.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

@Autowired
    private JournalEntryRepository journalEntryRepository;

@Autowired
private UserService userService;

@Transactional
public void saveEntry(JournalEntry journalEntry, String userName){
    try{
        User user =userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        //user.setUserName(null);
        userService.saveUser(user);
    }catch(Exception e){
        if (log.isErrorEnabled()) log.error("Error while saving journal entry", e);
    }

}
public void saveEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry);
}

public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
}

public Optional<JournalEntry> findById(ObjectId id){
    return journalEntryRepository.findById(id);
}

public void deleteById(ObjectId id,String userName){
    User user =userService.findByUserName(userName);
    user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
    userService.saveUser(user);
    journalEntryRepository.deleteById(id);
}

public List<JournalEntry> findByUserName(String userName){
    User user = userService.findByUserName(userName);
    return user != null ? user.getJournalEntries() : new ArrayList<>();

}

}
