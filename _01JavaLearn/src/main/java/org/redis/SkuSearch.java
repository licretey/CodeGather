package org.redis;// package org.redis;
//
// import org.apache.commons.lang3.StringUtils;
//
// import java.util.List;
//
// public class SkuSearch {
//     /**
//      * 从数据库调用
//      *
//      * @param skuId
//      * @return
//      */
//     public PmsSkuInfo getSkuByIdFromDb(String skuId) {
//         //sku的商品对象
//         PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
//         pmsSkuInfo.setId(skuId);
//         PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);
//
//         try {
//             //sku的图片集合
//             PmsSkuImage pmsSkuImage = new PmsSkuImage();
//             List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
//             skuInfo.setSkuImageList(pmsSkuImages);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return skuInfo;
//
//     }
//
//
//
//     /**
//      * 商品详细图
//      * 主要是item前端的东西，调用此处的服务，方便
//      * 使用Redis缓存，解决高并发
//      *
//      * @param skuId
//      * @return
//      */
//     @Override
//     public PmsSkuInfo getSkuById(String skuId) {
//
//         PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
//
//         //链接缓存
//         Jedis jedis = redisUtil.getJedis();
//
//         //查询缓存
//         String skuKey = "sky:" + skuId + ":info";
//         String skuJson = jedis.get("skuKey");
//         //可以吧json的字符串转换成jav的对象类
//         if (StringUtils.isNotBlank(skuJson)) {// if (skuJson!=null&&!skuJson.equals(""))
//             pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
//         } else {
//             //如果缓存没有，查询mysql
//             pmsSkuInfo = this.getSkuByIdFromDb(skuId);
//
//             if (pmsSkuInfo != null) {
//                 //mysql查询结果存入redis
//                 jedis.set("sku" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));
//             }
//         }
//
//
//         jedis.close();
//
//         return pmsSkuInfo;
//     }
// }
//
//
