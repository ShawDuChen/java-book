package com.action;

import com.model.Cart;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CartService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/cart")
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
public class CartAction extends BaseActionSupport<Cart> implements ModelDriven<Cart> {
    @Autowired
    private CartService cartService;
    private Cart model = new Cart();
    private List<Cart> list;

    public void setList(List<Cart> list) {
        this.list = list;
    }

    public List<Cart> getList() {
        return list;
    }

    @Override
    public Cart getModel() {
        return model;
    }

    public void setModel(Cart model) {
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
    public Cart getData() {
        return super.getData();
    }

    @Action(value = "all", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String getAll() {
        try {
            List<Cart> _list = cartService.getAll();
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "list", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String queryList() {
        try {
            int page = Integer.parseInt(getQuery("page"));
            int limit = Integer.parseInt(getQuery("limit"));
            List<Cart> _list = cartService.search(page, limit);
            setList(_list);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "get")
    public String findById() {
        try {
            long id = Long.parseLong(getQuery("id"));
            Cart cart = cartService.findById(id);
            actionSuccess(cart);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "create")
    public String create() {
        try {
            Cart model = getModel();
            cartService.add(model);
            actionSuccess(model);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "update")
    public String update() {
        try {
            Cart model = getModel();
            cartService.update(model);
            actionSuccess(model);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "delete")
    public String delete() {
        try {
            long id = Long.parseLong(getQuery("id"));
            cartService.delete(id);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
