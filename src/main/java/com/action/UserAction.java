package com.action;

import com.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Namespace("/user")

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
public class UserAction extends BaseActionSupport<User> implements ModelDriven<User> {
    @Autowired
    private UserService userService;
    private User model = new User();
    private List<User> list;

    public void setList(List<User> list) {
        this.list = list;
    }

    public List<User> getList() {
        return list;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getCode() {
        return super.getCode();
    }

    @Override
    public User getData() {
        return super.getData();
    }

    @Override
    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    @Action(value = "all", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code,message",
                    "excludeProperties", "model"
            }),
    })
    public String getAll() {
        try {
            List<User> _list = userService.getAll();
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "create", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String create() {
        try {
            User user = getModel();
            userService.add(user);
            actionSuccess(user);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "update", interceptorRefs = {
            @InterceptorRef("json")
    })
    public String update() {
        try {
            User user = getModel();
            userService.update(user);
            actionSuccess(user);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "delete")
    public String delete() {
        try {
            userService.delete(Long.parseLong(getQuery("id")));
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "list", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code,message",
                    "excludeProperties", "model"
            }),
    })
    public String queryList() {
        try {
            int page = Integer.parseInt(getQuery("page"));
            int limit = Integer.parseInt(getQuery("limit"));
            List<User> _list = userService.search(page, limit);
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

}
