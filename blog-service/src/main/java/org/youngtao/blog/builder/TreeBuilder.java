package org.youngtao.blog.builder;

import java.util.ArrayList;
import java.util.List;

import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.data.domain.user.Permissions;

public class TreeBuilder {

    /** 
    * 使用递归方法建树 
    * @param treeNodes 
    * @return 
    */  
   public static List<Permissions> buildByRecursive(List<Permissions> treeNodes) {  
       List<Permissions> trees = new ArrayList<Permissions>();  
       for (Permissions treeNode : treeNodes) {  
           if (Constants.PERMISSIONS_PARENT_ID == treeNode.getParentId() && Constants.PERMISSIONS_STATUS.equals(treeNode.getPmsOrCtl())) {  
               trees.add(findChildren(treeNode,treeNodes));  
           }  
       }  
       return trees;  
   }  
 
   /** 
    * 递归查找子节点 
    * @param treeNodes 
    * @return 
    */  
   public static Permissions findChildren(Permissions treeNode,List<Permissions> treeNodes) {  
       for (Permissions it : treeNodes) {  
           if(treeNode.getId().equals(it.getParentId()) && Constants.PERMISSIONS_STATUS.equals(it.getPmsOrCtl())) {  
               if (treeNode.getChildPermissions() == null) {  
                   treeNode.setChildPermissions(new ArrayList<Permissions>());  
               }  
               treeNode.getChildPermissions().add(findChildren(it,treeNodes));  
           }  
       }  
       return treeNode;  
   }  
}
