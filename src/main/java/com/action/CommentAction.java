package com.action;

import com.model.Comment;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CommentService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/comment")
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
@InterceptorRefs({
        @InterceptorRef("json")
})
@Component
public class CommentAction extends BaseActionSupport<Comment> implements ModelDriven<Comment> {
    @Autowired
    private CommentService commentService;
    private Comment model = new Comment();
    private List<Comment> list;

    public void setList(List<Comment> list) {
        this.list = list;
    }

    public List<Comment> getList() {
        return list;
    }

    public void setModel(Comment model) {
        this.model = model;
    }

    @Override
    public Comment getModel() {
        return model;
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
    public Comment getData() {
        return super.getData();
    }

    @Action(value = "create")
    public String create() {
        try {
            commentService.add(getModel());
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "update")
    public String update() {
        try {
            commentService.update(getModel());
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "delete")
    public String delete() {
        try {
            commentService.delete(Long.parseLong(getQuery("id")));
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "all", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String all() {
        try {
            List<Comment> _list = commentService.all(getQuery("productId"), getQuery("userId"));
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "productComments", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String productComments() {
        try {
            List<Comment> _list = commentService.findProductComments(getQuery("productId"));
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "userComments", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String userComments() {
        try {
            List<Comment> _list = commentService.findUserComments(getQuery("userId"));
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
