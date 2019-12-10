import java.util.*;

public class LeeCode {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode (int val){
            this.val = val;
        }
    }

    /**
     * 恢复二叉搜索树
     * 二叉搜素树的特点是他的中序遍历是一个递增的序列
     * 所以只需要找到它不是递增的序列进行交换就可以恢复二叉搜索树
     *
     * 交换的位置只有两种可能性：
     * 1、相邻的两个数字交换
     * 2、不相邻的两个数字进行交换（第一个逆序对的第一个数字和第二个逆序对的第二个数字）
     * 所以在中序遍历中，只需要一个pre节点和当前的节点比较，如果pre节点的值大于当前节点的值，就是我们要找的你须得数字
     * 如果有第二个数字就把second更新为当前节点
     */
    public void recoverTree(TreeNode root) {
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        //第一个节点记录第一个不是递增的节点
        TreeNode firstNode = null;
        //第二个节点记录第二个不是递增的节点
        TreeNode secondNode = null;
        TreeNode pre = null;
        TreeNode p = root;
        while(p!=null || !stack.isEmpty()){
            while(p!=null){
                stack.push(p);
                p = p .left;
            }
            p = stack.pop();
            //pre记录前一个节点，p记录当前节点
           if(pre !=null && p.val < pre.val){
               if(firstNode == null){
                   firstNode = pre;
                   secondNode = p;
               }else
                   secondNode = p;
           }
            pre = p;
            p = p.right;
        }
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }


    /**
     *  验证二叉搜索数  -》 二叉搜索树的中序遍历为升序序列
     * @param root 根节点
     * @return 是否是二叉搜索数
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack    = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode top = stack.pop();
            list.add(top.val);
            cur = top.right;
        }
        for(int i = 1 ; i < list.size() ; i++){
            if(list.get(i-1)> list.get(i)){
                return false;
            }
        }
        return true;
    }
    //使用递归判断是否是二叉搜索树
    //使用条件： 一个二叉搜索树的左子树和右子树也是二叉搜索树 --   左子树比根小  右子树比根大
    public boolean isValidBSTs(TreeNode root) {
        return validdata(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }

    private boolean validdata(TreeNode root, long maxValue, long minValue) {
        if(root == null){
            return true;
        }
        if(root.val <= minValue || root.val >= maxValue){
            return false;
        }
        //去左子树的话 当前的根节点是最大的值，去右子树，则根节点是最小的数
        return validdata(root.left,maxValue,root.val) && validdata(root.right,root.val,minValue);
    }

}
