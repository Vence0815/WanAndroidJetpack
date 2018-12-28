package com.renrun.basedevelopjetpack.data;

import java.util.List;

/**
 * Created by vence on 2018/7/23 09:32
 * 邮箱 ：vence0815@foxmail.com
 */
public class HomePageInfo extends BaseResponse{

    /**
     * total : 6
     * size : 10
     * pages : 1
     * current : 1
     * records : [{"goodsCommonid":16,"goodsName":"iPhone10","gcId":11,"gcId1":2,"gcId1Name":null,"gcId2":3,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"293","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18}]","goodsState":1,"goodsPrice":3200,"freight":12,"planId":2,"planName":"超级买买买","shelfTime":"2018-07-19 14:52:38","firstAccount":0,"eachPeriodAccount":0,"leasePeriod":0,"leasePeriodType":0,"buyoutPrice":0},{"goodsCommonid":14,"goodsName":"iPhone10","gcId":11,"gcId1":2,"gcId1Name":null,"gcId2":3,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"293","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18}]","goodsState":1,"goodsPrice":3200,"freight":12,"planId":2,"planName":"超级买买买","shelfTime":"2018-07-19 14:47:46","firstAccount":0,"eachPeriodAccount":0,"leasePeriod":0,"leasePeriodType":0,"buyoutPrice":0},{"goodsCommonid":13,"goodsName":"iPhone10","gcId":11,"gcId1":2,"gcId1Name":null,"gcId2":3,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"293","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18}]","goodsState":1,"goodsPrice":3200,"freight":12,"planId":2,"planName":"超级买买买","shelfTime":"2018-07-19 14:34:48","firstAccount":0,"eachPeriodAccount":0,"leasePeriod":0,"leasePeriodType":0,"buyoutPrice":0},{"goodsCommonid":12,"goodsName":"iPhone11","gcId":11,"gcId1":8,"gcId1Name":null,"gcId2":10,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"297","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18}]","goodsState":1,"goodsPrice":3000,"freight":15,"planId":10,"planName":"1成首付iphoneX 超划算","shelfTime":"2018-07-19 14:11:51","firstAccount":0,"eachPeriodAccount":0,"leasePeriod":0,"leasePeriodType":0,"buyoutPrice":0},{"goodsCommonid":11,"goodsName":"iPhone11","gcId":11,"gcId1":8,"gcId1Name":null,"gcId2":10,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"297","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18,\"attrValueName\":\"8英寸\"}]","goodsState":1,"goodsPrice":3000,"freight":15,"planId":10,"planName":"1成首付iphoneX 超划算","shelfTime":"2018-07-19 14:08:46","firstAccount":0,"eachPeriodAccount":0,"leasePeriod":0,"leasePeriodType":0,"buyoutPrice":0},{"goodsCommonid":10,"goodsName":"iPhone10","gcId":11,"gcId1":8,"gcId1Name":null,"gcId2":10,"gcId2Name":null,"gcId3":11,"gcId3Name":null,"gcName":"手机","goodsImage":"293","brandId":6,"brandName":"苹果","typeId":11,"goodsAttr":"[{\"attrId\":9,\"attrName\":\"屏幕尺寸\",\"attrValueId\":18,\"attrValueName\":\"8英寸\"}]","goodsState":1,"goodsPrice":3200,"freight":12,"planId":2,"planName":"超级买买买","shelfTime":"2018-07-19 10:19:36","firstAccount":640,"eachPeriodAccount":160,"leasePeriod":12,"leasePeriodType":5,"buyoutPrice":1440}]
     */

    private int total;
    private int size;
    private int pages;
    private int current;
    private List<RecordsEntity> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<RecordsEntity> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsEntity> records) {
        this.records = records;
    }

    public static class RecordsEntity {
        /**
         * goodsCommonid : 16
         * goodsName : iPhone10
         * gcId : 11
         * gcId1 : 2
         * gcId1Name : null
         * gcId2 : 3
         * gcId2Name : null
         * gcId3 : 11
         * gcId3Name : null
         * gcName : 手机
         * goodsImage : 293
         * brandId : 6
         * brandName : 苹果
         * typeId : 11
         * goodsAttr : [{"attrId":9,"attrName":"屏幕尺寸","attrValueId":18}]
         * goodsState : 1
         * goodsPrice : 3200
         * freight : 12
         * planId : 2
         * planName : 超级买买买
         * shelfTime : 2018-07-19 14:52:38
         * firstAccount : 0
         * eachPeriodAccount : 0
         * leasePeriod : 0
         * leasePeriodType : 0
         * buyoutPrice : 0
         */

        private String goodsCommonid;
        private String goodsName;
        private String goodsImage;
        private String brandName;
        private String typeId;
        private String goodsPrice;
        private String planId;
        private String planName;
        private String firstAccount;
        private String eachPeriodAccount;
        private String leasePeriod;
        private String leasePeriodType;

        public String getGoodsCommonid() {
            return goodsCommonid;
        }

        public void setGoodsCommonid(String goodsCommonid) {
            this.goodsCommonid = goodsCommonid;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getFirstAccount() {
            return firstAccount;
        }

        public void setFirstAccount(String firstAccount) {
            this.firstAccount = firstAccount;
        }

        public String getEachPeriodAccount() {
            return eachPeriodAccount;
        }

        public void setEachPeriodAccount(String eachPeriodAccount) {
            this.eachPeriodAccount = eachPeriodAccount;
        }

        public String getLeasePeriod() {
            return leasePeriod;
        }

        public void setLeasePeriod(String leasePeriod) {
            this.leasePeriod = leasePeriod;
        }

        public String getLeasePeriodType() {
            return leasePeriodType;
        }

        public void setLeasePeriodType(String leasePeriodType) {
            this.leasePeriodType = leasePeriodType;
        }

        public String getBuyoutPrice() {
            return buyoutPrice;
        }

        public void setBuyoutPrice(String buyoutPrice) {
            this.buyoutPrice = buyoutPrice;
        }

        private String buyoutPrice;


    }
}
