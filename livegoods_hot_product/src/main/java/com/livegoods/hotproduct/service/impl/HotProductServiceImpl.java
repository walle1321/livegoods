package com.livegoods.hotproduct.service.impl;

import com.jyj.livegoods.pojo.Item;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.hotproduct.dao.ItemDao;
import com.livegoods.hotproduct.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private ItemDao itemDao;
    @Value("${livegoods.banner.nginx.host}")
    private String nginxHost;

    @Override
    public LivegoodsResult showHotProducts(String city) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("city").is(city)
        );
        query.with(
                PageRequest.of(0,4,
                        Sort.by(Sort.Direction.DESC,"sales"))
        );

        List<Item> result = itemDao.selectItem(query);
        int size = result.size();
        if (size<4){
            query.addCriteria(
                    Criteria.where("city").ne(city)
            );
            query.with(
                    PageRequest.of(0,4-size,
                            Sort.by(Sort.Direction.DESC,"sales"))
            );
            List<Item> otherResult = itemDao.selectItem(query);
            result.addAll(otherResult);
        }
        if (result.size()<4){
            for (int i = result.size(); i <= 4; i++) {
                result.add(this.fallbackItem());
            }
        }
        System.out.println(result);
        List<Item> list = changeImgsUrl(result);
        return LivegoodsResult.okData(list);
    }


    //获取托底数据
    private Item fallbackItem(){
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("北京");
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
        item.setTitle("北京高档公寓");
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
