package org.rabbitstack.rabbit.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller is basically for serving up
 * the Angular views.
 *
 * @author Nedim Sabic
 */
@Controller
public class IndexController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
