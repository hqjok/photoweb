package com.photo.web.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Create 2020-01-19 15:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterVO {

    private String username;
    private String loginacct;
    private String userpswd;
    private String sex;
    private String email;
    private String address;
}
