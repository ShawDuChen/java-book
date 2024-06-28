package com.action;

import com.model.Category;
import com.opensymphony.xwork2.ModelDriven;
import com.service.CategoryService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/category")
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
public class CategoryAction extends BaseActionSupport<Category> implements ModelDriven<Category> {
    @Autowired
    private CategoryService categoryService;
    private Category model = new Category();
    private List<Category> list;

    public void setList(List<Category> list) {
        this.list = list;
    }

    public List<Category> getList() {
        return list;
    }

    @Override
    public Category getModel() {
        return model;
    }

    public void setModel(Category model) {
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
    public Category getData() {
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
            String name = getQuery("name");
            List<Category> _list = categoryService.getAll(name);
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
            List<Category> _list = categoryService.search(page, limit);
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
            Category category = categoryService.findById(id);
            actionSuccess(category);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "create")
    public String create() {
        try {
            Category model = getModel();
            categoryService.add(model);
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
            Category model = getModel();
            categoryService.update(model);
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
            categoryService.delete(id);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
