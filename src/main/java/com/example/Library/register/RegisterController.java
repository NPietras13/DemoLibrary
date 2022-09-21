package com.example.Library.register;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public List<Register> getEntries(){
        return registerService.getEntries();
    }

    @GetMapping(path = "{EntryId}")
    public Optional<Register> getEntry(@PathVariable("EntryId") Long entryId){
        Optional<Register> optionalRegister = registerService.getEntry(entryId);
        if(optionalRegister.isEmpty())throw new IllegalStateException("Entry with id: "+entryId+" does not exist");
        return registerService.getEntry(entryId);
    }

    @GetMapping(path = "/byname")
    public List<Register> getEntriesByNameOfBook(@RequestParam String name){
        return registerService.getEntriesByNameOfBook(name);
    }

    @PostMapping
    public void addEntry(@RequestBody Register register){
        registerService.addEntry(register);
    }

    @DeleteMapping(path = "{EntryId}")
    public void deleteEntry(@PathVariable("EntryId")Long entryId){
        registerService.deleteEntry(entryId);
    }
}
