package com.example.booksapp.web;

import com.example.booksapp.model.Book;
import com.example.booksapp.model.dto.BookDto;
import com.example.booksapp.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController (BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return this.bookService.getAll();
    }
    @GetMapping("/pagination")
    public List<Book> findAllWithPagination(Pageable pageable){
        return this.bookService.findAllWithPagination(pageable).getContent();
    }
    @GetMapping ("/{id}")ResponseEntity<Book> getBookById(@PathVariable Long id){
        return  this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/dto/{id}") ResponseEntity<BookDto> getBookDto(@PathVariable Long id){
        return this.bookService.findBookDto(id)
                .map(bookDto -> ResponseEntity.ok().body(bookDto))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto book){
        return this.bookService.save(book)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        Optional<Book> book = this.bookService.findById(id);
        if(!book.isEmpty()){
            this.bookService.delete(id);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookDto> editBook(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/borrow/{id}")
    public ResponseEntity borrowBook(@PathVariable Long id)
    {
        this.bookService.borrowBook(id);
        return new ResponseEntity("Borrowed",HttpStatus.OK);
    }
}
