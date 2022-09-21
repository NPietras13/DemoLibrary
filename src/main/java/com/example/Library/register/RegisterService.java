package com.example.Library.register;

import com.example.Library.book.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final BookRepository bookRepository;

    public RegisterService(RegisterRepository registerRepository, BookRepository bookRepository) {
        this.registerRepository = registerRepository;
        this.bookRepository = bookRepository;
    }


    public List<Register> getEntries(){
        return registerRepository.findAll();
    }

    public Optional<Register> getEntry(Long entryId){
        Optional<Register> optionalRegister = registerRepository.findById(entryId);
        if(optionalRegister.isEmpty())throw new IllegalStateException("Entry with id: "+entryId+" does not exist");
        return optionalRegister;
    }

    public List<Register> getEntriesByNameOfBook(String name){
        return registerRepository.findRegistersByBookName(name);
    }


    public void addEntry(Register register){
        register.setDateOfBorrow(LocalDate.now());
        if(register.getNameOfClient()==null || register.getNameOfClient().length()==0)throw new IllegalStateException("name of client is missing");
        if(bookRepository.findById(register.getBookId()).isEmpty())throw new IllegalStateException("book with id: "+register.getBookId()+" does not exist");

        registerRepository.save(register);
    }

    public void deleteEntry(Long entryId){
        Optional<Register> optionalRegister = registerRepository.findById(entryId);
        if(optionalRegister.isEmpty())throw new IllegalStateException("Entry with id: "+entryId+" does not exist");
        registerRepository.deleteById(entryId);
    }

}
