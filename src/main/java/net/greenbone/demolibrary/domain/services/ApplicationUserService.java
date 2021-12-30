package net.greenbone.demolibrary.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.enums.Role;
import net.greenbone.demolibrary.domain.services.helper.MapperDtoToEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUser getUserById(Long id){
        try{
            return applicationUserRepository.findById(id).get();
        }catch(NullPointerException nullPointerException){
            return null;
        }
    }

    public ApplicationUser createNewUser(ApplicationUser.Create user){
        try{
            ApplicationUser newUser = MapperDtoToEntity.userRequestToUser(user);
            ApplicationUser createdNewUser = applicationUserRepository.save(newUser);
            return createdNewUser;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }

    public boolean updateUser(Long id, ApplicationUser.Update user){
        try{
            ApplicationUser toUpdate = applicationUserRepository.findById(id).get();

            toUpdate.setEmail(user.getEmail());
            toUpdate.setLateFees(user.getLateFees());
            toUpdate.setPassword(user.getPassword());
            toUpdate.setBorrowedBooks(user.getBorrowedBooks());
            toUpdate.setName(user.getName());
            toUpdate.setRole(Role.valueOf(user.getRole()));

            applicationUserRepository.save(toUpdate);
            return true;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return false;
        }
    }

    public ApplicationUser deleteUserWithId(Long id){
        try{
            ApplicationUser deletedBook = applicationUserRepository.findById(id).get();
            applicationUserRepository.deleteById(id);
            return deletedBook;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }
}
