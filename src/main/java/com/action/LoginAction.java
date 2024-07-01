package com.action;

import com.model.User;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import com.utils.BaseActionSupport;
import com.utils.JwtTokenGenerator;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Namespace("/auth")
@ParentPackage("json-default")
@Results({
        @Result(name = "success", type = "json", params = {
                "includeProperties", "data.*,code,message,token",
                "excludeProperties", "model"
        }),
        @Result(name = "error", type = "json", params = {
                "includeProperties", "data.*,code,message,token",
                "excludeProperties", "model"
        })
})
@Component
public class LoginAction extends BaseActionSupport<User> implements ModelDriven<User> {
    @Autowired
    private UserService userService;
    private User model = new User();
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Action(value = "login", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String execute() throws Exception {
        try {
            User user = userService.login(getModel());
            if (user != null) {
                setToken(JwtTokenGenerator.generateToken(user.getUsername()));
                actionSuccess(user);
                return SUCCESS;
            } else {
                actionError();
                return ERROR;
            }
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "register", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String register() throws Exception {
        try {
            userService.register(getModel());
            actionSuccess(getModel());
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "verify", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String verify() throws Exception {
        try {
            User user = userService.verify(getModel());
            if (user == null) {
                actionError();
                return ERROR;
            }
            actionSuccess(user);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "resetPassword", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String resetPassword() throws Exception {
        try {
            userService.resetPassword(getModel());
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
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
