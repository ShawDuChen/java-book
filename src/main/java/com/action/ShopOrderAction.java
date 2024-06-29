package com.action;

import com.model.Cart;
import com.model.ShopOrder;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ShopOrderService;
import com.utils.BaseActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Namespace("/shopOrder")
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
public class ShopOrderAction extends BaseActionSupport<ShopOrder> implements ModelDriven<ShopOrder> {
    @Autowired
    private ShopOrderService shopOrderService;
    private ShopOrder model = new ShopOrder();
    private List<ShopOrder> list;
    private List<Long> ids;
    private long userId;

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setList(List<ShopOrder> list) {
        this.list = list;
    }

    public List<ShopOrder> getList() {
        return list;
    }

    @Override
    public ShopOrder getModel() {
        return model;
    }

    public void setModel(ShopOrder model) {
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
    public ShopOrder getData() {
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
            List<ShopOrder> _list = shopOrderService.getAll();
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
            List<ShopOrder> _list = shopOrderService.search(page, limit);
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
            ShopOrder shopOrder = shopOrderService.findById(id);
            actionSuccess(shopOrder);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "create")
    public String create() {
        try {
            ShopOrder model = getModel();
            shopOrderService.add(model);
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
            ShopOrder model = getModel();
            shopOrderService.update(model);
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
            shopOrderService.delete(id);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action("createOrder")
    public String createOrder() {
        try {
            ShopOrder order = shopOrderService.createOrder(getIds(), getUserId());
            actionSuccess(order);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }

    @Action(value = "pay")
    public String payOrder() {
        try {
            long id = Long.parseLong(getQuery("id"));
            shopOrderService.pay(id);
            actionSuccess(null);
            return SUCCESS;
        } catch (HibernateException e) {
            actionError();
            return ERROR;
        }
    }
}
