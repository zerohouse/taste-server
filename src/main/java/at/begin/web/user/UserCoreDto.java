package at.begin.web.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCoreDto {
    long id;
    String name;
    String email;
    String gender;
    String photo;
    String introduce;
    Integer age;
    String district;
    Boolean matching;
    String phone;

    public UserCoreDto(User user) {
        id = user.id;
        name = user.name;
        email = user.email;
        gender = user.gender;
        introduce = user.introduce;
        photo = user.photo;
        age = user.age;
        district = user.district;
        phone = user.phone;
        matching = user.matching;
    }

}
