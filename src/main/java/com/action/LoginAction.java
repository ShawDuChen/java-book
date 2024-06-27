package com.action;

import com.model.User;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Namespace("/")
@ParentPackage("json-default")
@Results({
        @Result(name = "success", type = "json", params = {
                "includeProperties", "data.*,code,message",
                "excludeProperties", "model"
        }),
        @Result(name = "error", type = "json", params = {
                "includeProperties", "data.*,code,message",
                "excludeProperties", "model"
        })
})
@Component
public class LoginAction extends BaseActionSupport<User> implements ModelDriven<User> {
    @Autowired
    private UserService userService;
    private User model = new User();

    @Action(value = "login", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String execute() throws Exception {
        User user = getModel();
        if (userService.login(user) != null) {
            actionSuccess(user);
            return SUCCESS;
        } else {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "register", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String register() throws Exception {
        userService.register(getModel());
        return SUCCESS;
    }

    @Override
    public User getModel(){
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    @Override
    public int getCode() {
        return super.getCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public User getData() {
        return super.getData();
    }
}
