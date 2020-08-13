//package com.livegoods.test;
//
//import com.jyj.livegoods.pojo.Item;
//import com.livegoods.search.SearchApp;
//import com.livegoods.search.dao.ESDao;
//import com.livegoods.search.pojo.Item4ES;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest(classes = {SearchApp.class})
//@RunWith(SpringRunner.class)
//public class SearchTest {
//
//    @Autowired
//    private ESDao esDao;
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Value("${livegoods.banner.nginx.host}")
//    private String nginxHost;
//
//    /**
//     * 从MongoDB中查询商品数据，
//     * <p>
//     * 数据转换为Item4ES
//     * <p>
//     * 将数据添加到ES
//     */
//    @Test
//    public void insertES() {
//
//        List<Item> items = mongoTemplate.findAll(Item.class);
//
//        List<Item4ES> item4ESList = new ArrayList<>();
//        for (Item item : items) {
//            Item4ES item4ES = new Item4ES();
//            BeanUtils.copyProperties(item,item4ES);
//            item4ES.setHouseType(item.getHouseType4Search());
//            item4ES.setPrice("<h3>"+item.getPrice()+"</h3>");
//            item4ES.setImg(nginxHost+item.getImg());
//            System.out.println(item4ES.toString());
//            item4ESList.add(item4ES);
//        }
//
//        esDao.insertBatchItem(item4ESList);
//
//    }
//}
