// package com.licretey.utils;
//
//
// import logwire.api.Context;
// import logwire.api.database.SizableIterable;
// import logwire.api.payload.ActionResponse;
// import logwire.api.payload.Response;
// import logwire.api.process.Action;
// import logwire.products.es_tms.actionPayload.supplyChain.ROBasePayload;
// import logwire.products.es_tms.choice.ChainOwnerCategory;
// import logwire.records.es_tms.model.SupplyChainOrderRelease;
// import org.springframework.stereotype.Component;
//
// @Component
// public class GetBatchHad extends Action<ROBasePayload> {
//
//
//     @Override
//     public Response process(Context ctx, ROBasePayload payload) {
//
//         SizableIterable<SupplyChainOrderRelease> ros = SupplyChainOrderRelease.listBySupplyChainOwnerAndSupplyChainEquipment(ctx,payload.getOwnerId(), payload.getId());
//         int size = 0;
//         if(ChainOwnerCategory.CATEGORY2.equals(payload.getStatus())){
//             size = ros.sumInt(ro ->{
//                 return payload.getCode().equals(ro.getBatchNo()) ? ro.getUnitCount() : 0 ;
//             });
//         }else {
//             size = ros.sumInt(ro ->{
//                 return ro.getUnitCount();
//             });
//         }
//         return new ActionResponse(size);
//     }
//
//     @Override
//     public String getName() {
//         return "warnIsHadBathc";
//     }
//
//     @Override
//     public boolean isAccessControlled() {
//         return false;
//     }
//
//     @Override
//     public boolean isAnonymousAccessible() {
//         return true;
//     }
// }
