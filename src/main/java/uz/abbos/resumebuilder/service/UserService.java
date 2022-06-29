package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.UserDao;
import uz.abbos.resumebuilder.dto.UserDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.User;

import java.util.Optional;

@Service("userService")
public class UserService {

    @Autowired
    public UserService(ModelMapper modelMapper, UserDao userDao) {
        this.modelMapper = modelMapper;
        this.userDao = userDao;
    }

    private final ModelMapper modelMapper;

    private final UserDao userDao;


    @Transactional
    public UserDto get(Long id) {
        User user =  userDao.findUser(id);
        if (user == null){
            throw new BadRequestException("User not found");
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }


    @Transactional
    public String create(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            return userDao.saveUser(user);
        }
        catch (Exception e){
            throw new BadRequestException("User create failed");
        }
    }

    @Transactional
    public String update(UserDto userDto) {
        try{
            User user = modelMapper.map(userDto,User.class);
            return userDao.updateUser(user);
        }
        catch (Exception e){
            throw new BadRequestException("User update failed");
        }
    }


    @Transactional
    public String delete(Long id) {
            User user = check(id);
            return userDao.deleteUser(user);
    }


    public User check(Long id) {
        Optional<User> optional = Optional.ofNullable(userDao.findUser(id));
        if (optional.isEmpty()){
            throw new BadRequestException("User not found");
        }
        return optional.get();
    }
}


