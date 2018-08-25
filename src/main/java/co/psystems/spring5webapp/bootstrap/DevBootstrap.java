package co.psystems.spring5webapp.bootstrap;

import co.psystems.spring5webapp.model.Author;
import co.psystems.spring5webapp.model.Book;
import co.psystems.spring5webapp.model.Publisher;
import co.psystems.spring5webapp.repositories.AuthorRepository;
import co.psystems.spring5webapp.repositories.BookRepository;
import co.psystems.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;


    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("------------->: Populating Data..");
        initData();
    }

    private void initData() {
        //eric
        Author eric = new Author("Eric", "Evans");
        Publisher harperCollins = new Publisher("Harper Collins");
        Book book = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(harperCollins);
        bookRepository.save(book);

        //rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx");
        Book noEjb = new Book("J2EE Development without EJB", "2345", worx);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(worx);
        bookRepository.save(noEjb);

    }
}
