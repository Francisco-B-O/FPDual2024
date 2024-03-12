package com.dual2024.projectcompetition.DAO;

import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.dataaccess.model.User;
import com.dual2024.projectcompetition.utils.UserState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(showSql = false)
@ComponentScan(basePackages = "com.Dual2024.ProjectCompetition.DataAccess.DAO")
public class UserDAOTest {
    @Autowired
    private UserDAO userDao;

    private User user, user2, duplicatedNickUser, duplicatedEmailUser, invalidPasswordUser;

    @BeforeEach
    public void setup() {
        user = User.builder().email("test@email.com").nick("test").password("passwordTest").state(UserState.CONNECTED)
                .build();
        user2 = User.builder().email("test2@email.com").nick("test2").password("passwordTest2")
                .state(UserState.CONNECTED).build();
        duplicatedNickUser = User.builder().email("test2@email.com").nick("test").password("passwordTest2")
                .state(UserState.CONNECTED).build();
        duplicatedEmailUser = User.builder().email("test@email.com").nick("test2").password("passwordTest2")
                .state(UserState.CONNECTED).build();
        invalidPasswordUser = User.builder().email("test3@email.com").nick("test3").password("passw")
                .state(UserState.CONNECTED).build();
    }

    @Test
    @DisplayName("findById operation")
    public void givenId_whenFindById_theReturnUser() throws DataException {

        User savedUser = userDao.save(user);


        User foundUser = userDao.findById(user.getId());


        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @DisplayName("save operation")
    public void givenUserObject_whenSave_theReturnSavedUser() throws DataException {

        User savedUser = userDao.save(user);

        assertThrows(DataException.class, () -> userDao.save(duplicatedEmailUser));
        assertThrows(DataException.class, () -> userDao.save(duplicatedNickUser));
        assertThrows(DataException.class, () -> userDao.save(invalidPasswordUser));
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("findAll operation")
    public void givenUsersList_whenFindAll_theReturnUsersList() throws DataException {

        userDao.save(user);
        userDao.save(user2);

        List<User> users = userDao.findAll();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByEmail operation")
    public void givenUser_whenFindByEmail_theReturnUser() throws DataException {

        User savedUser = userDao.save(user);

        User foundUser = userDao.findByEmail("test@email.com");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @DisplayName("findByNick operation")
    public void givenUser_whenFindByNick_theReturnUser() throws DataException {

        User savedUser = userDao.save(user);

        User foundUser = userDao.findByNick("test");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @DisplayName("findByNickOrEmail operation")
    public void givenUser_whenFindByEmailOrNick_theReturnUser() throws DataException {

        User savedUser = userDao.save(user);

        User foundUser = userDao.findByNickOrEmail("test", "no").getFirst();

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
    }

    @Test
    @DisplayName("update operation")
    public void givenUser_whenUpdate_theReturnUpdatedUser() throws DataException {

        userDao.save(user);
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setEmail("updated@email.com");
        updatedUser.setNick("updated");
        updatedUser.setPassword("updatedPassword");
        updatedUser.setState(UserState.CONNECTED);

        User savedUpdatedUser = userDao.save(updatedUser);

        assertThat(savedUpdatedUser).isNotNull();
        assertThat(savedUpdatedUser).isEqualTo(updatedUser);
    }

    @Test
    @DisplayName("delete operation")
    public void givenUser_whenDelete_thenDeleteUser() throws DataException {

        userDao.save(user);

        userDao.delete(user.getId());

        assertThrows(DataException.class, () -> userDao.findByEmail("test@email.com"));
    }

}
