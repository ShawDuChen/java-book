package com.action;

import com.model.Product;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ProductService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/product")
@ParentPackage("default")
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
        @InterceptorRef("jwtToken"),
        @InterceptorRef("json")
})
@Component
public class ProductAction extends BaseActionSupport<Product> implements ModelDriven<Product> {
    @Autowired
    private ProductService productService;
    private Product model = new Product();
    private List<Product> list;

    public void setList(List<Product> list) {
        this.list = list;
    }

    public List<Product> getList() {
        return list;
    }

    @Override
    public Product getModel() {
        return model;
    }

    public void setModel(Product model) {
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
    public Product getData() {
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
            List<Product> _list = productService.getAll(getQuery("name"), getQuery("categoryId"));
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
            List<Product> _list = productService.search(page, limit);
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
            Product product = productService.findById(id);
            actionSuccess(product);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "create")
    public String create() {
        try {
            Product model = getModel();
            productService.add(model);
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
            Product model = getModel();
            productService.update(model);
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
            productService.delete(id);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "hotList", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String getHotList() {
        try {
            List<Product> _list = productService.getHots();
            setList(_list);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
