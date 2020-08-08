package com.livegoods.recommedation.service.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.recommedation.dao.RecommedationDao;
import com.livegoods.recommedation.service.RecommedationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommedationServiceImpl implements RecommedationService {

    @Autowired
    private RecommedationDao recommedationDao;
    @Value("${livegoods.banner.nginx.host}")
    private String nginxHost;

    @Override
    public LivegoodsResult showRecommedation(String city) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("city").is(city),
                Criteria.where("recommendation").is(true)
        );
        query.addCriteria(criteria);
        query.with(
                PageRequest.of(0,4,
                        Sort.by(Sort.Direction.DESC,"recoSort"))
        );
        List<Item> itemList = recommedationDao.select(query);
        System.out.println("第一次查询"+itemList);


        //如果本地的数量不足四个，在其他城市拿出来
        if (itemList.size()<4){
            Query otherQuery = new Query();
            Criteria otherCriteria = new Criteria();
            otherCriteria.andOperator(
                    Criteria.where("city").ne(city),
                    Criteria.where("recommendation").is(true)
            );
            otherQuery.addCriteria(otherCriteria);
            otherQuery.with(
                    PageRequest.of(0,4-itemList.size(),
                            Sort.by(Sort.Direction.DESC,"recoSort"))
            );
            List<Item> otherList = recommedationDao.select(otherQuery);
            System.out.println("otherList"+otherList);
            itemList.addAll(otherList);
        }

        //如果本地加其他城市都不足四个，使用托底数据
        if (itemList.size()<4){
            for (int i = itemList.size(); i < 4; i++) {
                itemList.add(this.fallbackItem());
            }
        }


        return LivegoodsResult.okData(this.changeImgsUrl(itemList));
    }


    //获取托底数据
    private Item fallbackItem(){
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("天津");
        item.setHouseType("150 ㎡");
        item.setImgs(
                Arrays.asList(
                        "group1/M00/00/00/wKgIgV8opiSASrSeABLGy04UWBI321.png",
                        "group1/M00/00/00/wKgIgV8oplmADSvRAAjIoXS-cuE587.png",
                        "group1/M00/00/00/wKgIgV8opmKADj6yAAro96E3Lio941.png"
                )
        );
        item.setPrice(12000L);
        item.setRecommendation(true);
        item.setRecoSort((byte)9);
        item.setRentType("整租");
        item.setSales(100L);
        item.setTitle("天津高档公寓");
        Map<String, String> info = new HashMap<>();
        info.put("years", "2010");
        info.put("type", "3室2厅");
        info.put("level", "10/18层");
        info.put("style", "精装修");
        info.put("orientation", "南北通透");
        item.setInfo(info);

        return item;
    }



    //将每个图片地址加上前缀
    private List<Item> changeImgsUrl(List<Item> list){
        for (Item item :
                list) {
            List<String> imgList = new ArrayList<>();
            for (String imgUrl :
                    item.getImgs()) {
                imgUrl = nginxHost + imgUrl;
                imgList.add(imgUrl);
            }
            item.setImgs(imgList);
        }
        return list;
    }
}
