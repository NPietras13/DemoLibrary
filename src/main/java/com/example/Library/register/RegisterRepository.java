package com.example.Library.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    @Query("SELECT r FROM Register r JOIN Book b ON r.bookId=b.id WHERE b.name=?1")
    List<Register> findRegistersByBookName(String name);
}
