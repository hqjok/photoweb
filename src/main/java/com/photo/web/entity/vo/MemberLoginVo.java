package com.photo.web.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Create 2020-01-17 8:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVo {

    private String loginacct;
    private String userpswd;

}
