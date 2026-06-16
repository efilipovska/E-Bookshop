package mk.uikm.finki.emtlab.service.domain.impl;

import mk.uikm.finki.emtlab.model.domain.Author;
import mk.uikm.finki.emtlab.model.exception.ResourceNotFoundException;
import mk.uikm.finki.emtlab.repository.AuthorRepository;
import mk.uikm.finki.emtlab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %d not found!", id)));
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        Author existing = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %d not found!", id)));
        existing.setName(author.getName());
        existing.setSurname(author.getSurname());
        existing.setCountry(author.getCountry());
        return authorRepository.save(existing);
    }

    @Override
    public Author delete(Long id) {
        Author existing = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %d not found!", id)));
        authorRepository.delete(existing);
        return existing;
    }
}
