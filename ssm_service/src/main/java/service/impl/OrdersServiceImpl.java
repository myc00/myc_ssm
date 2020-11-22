package service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import dao.IOrdersDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IOrdersService;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception{
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        Orders orders=ordersDao.findById(id);
        return orders;
    }
}
