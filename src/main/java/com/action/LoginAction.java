package com.action;

import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Namespace("/")
@ParentPackage("json-default")
@Results({
        @Result(name = "success", type = "json"),
        @Result(name = "error", type = "json")
})
@Component
public class LoginAction extends ActionSupport implements ModelDriven<User> {
    @Autowired
    private UserService userService;

    private User user = new User();

    @Action(value = "login", results = {
            @Result( name = "success", type = "json" )
    }, interceptorRefs = {
            @InterceptorRef("json")
    })
    public String execute() throws Exception {
        User user = getModel();
        if (userService.login(user) != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Action(value = "register")
    public String register() throws Exception {
        userService.register(getModel());
        return SUCCESS;
    }

    @Override
    public User getModel(){
        return user;
    }
}
