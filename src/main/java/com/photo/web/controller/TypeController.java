package com.photo.web.controller;

import com.photo.web.entity.po.TypePO;
import com.photo.web.entity.po.TypePOExample;
import com.photo.web.mapper.TypePOMapper;
import com.photo.web.util.HouseConstant;
import com.photo.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Create 2020-02-10 17:55
 */
@Controller
public class TypeController {

    @Autowired
    private TypePOMapper typePOMapper;


}
