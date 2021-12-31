package net.greenbone.demolibrary.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import net.greenbone.demolibrary.adapter.persistence.LendBookRepository;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.services.helper.MapperDtoToEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final LendBookRepository lendBookRepository;
    @Transactional
    public ApplicationUser getUserById(Long id){
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find Object with ID: " + id.toString()));
    }

    @Transactional
    public ApplicationUser createNewUser(ApplicationUser.Create user){
        ApplicationUser newUser;
        if(user.getBorrowedBooks() != null || !user.getBorrowedBooks().isEmpty()){
            List<LendBook> borrowedBooks = lendBookRepository.findAllById(user.getBorrowedBooks());
            newUser = ApplicationUser.fromCreate(user, borrowedBooks);
        }else{
            newUser = ApplicationUser.fromCreate(user, null);
        }
        ApplicationUser createdNewUser = applicationUserRepository.save(newUser);
        return createdNewUser;
    }

    @Transactional
    public void updateUser(Long id, ApplicationUser.Update user){
            //string formatting
        ApplicationUser toUpdate = applicationUserRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find Object with ID: " + id.toString()));
        List<LendBook> borrowedBooks = lendBookRepository.findAllById(user.getBorrowedBooks());
        toUpdate.fromUpdate(user, borrowedBooks);
        applicationUserRepository.save(toUpdate);
    }

    @Transactional
    public void deleteUserWithId(Long id){
        applicationUserRepository.deleteById(id);
    }
}
