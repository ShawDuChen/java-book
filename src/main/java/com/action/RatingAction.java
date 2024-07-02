package com.action;

import com.model.Rating;
import com.opensymphony.xwork2.ModelDriven;
import com.service.RatingService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/rating")
@ParentPackage("json-default")
@InterceptorRefs({
        @InterceptorRef("json")
})
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
public class RatingAction extends BaseActionSupport<Rating> implements ModelDriven<Rating> {
    @Autowired
    private RatingService ratingService;
    private Rating model = new Rating();
    private List<Rating> list;

    public void setList(List<Rating> list) {
        this.list = list;
    }

    public List<Rating> getList() {
        return list;
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
    public Rating getData() {
        return super.getData();
    }

    @Override
    public Rating getModel() {
        return model;
    }

    public void setModel(Rating model) {
        this.model = model;
    }

    @Action(value = "create")
    public String create() {
        try {
            Rating rating = ratingService.add(getModel());
            actionSuccess(rating);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "update")
    public String update() {
        try {
            Rating rating = ratingService.update(getModel());
            actionSuccess(rating);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "delete")
    public String delete() {
        try {
            ratingService.delete(Long.parseLong(getQuery("id")));
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
            List<Rating> _list = ratingService.all();
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "productRatings", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String productRatings() {
        try {
            List<Rating> _list = ratingService.findByProductId(getQuery("productId"));
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "userRatings", results = {
            @Result(name = "success", type = "json", params = {
                    "includeProperties", "list.*, code, message",
                    "excludeProperties", "model"
            }),
    })
    public String userRatings() {
        try {
            List<Rating> _list = ratingService.findByUserId(getQuery("userId"));
            actionSuccess(null);
            setList(_list);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
